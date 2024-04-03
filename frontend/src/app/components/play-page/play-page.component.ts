import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { LocalStorageService } from '../../services/local-storage.service';
import { MultipleChoiceService } from '../../services/multiple-choice.service';
import { multipleChoice } from '../../multipleChoice';
import { SingleChoiceService } from '../../services/single-choice.service';
import { singleChoice } from '../../singleChoice';
import { DropService } from '../../services/drop.service';
import { RequiredService } from '../../services/required.service';
import { storyObjectService } from '../../services/story-object.service';
import { inventory } from '../../inventory';
import { InventoryService } from '../../services/inventory.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatchService } from '../../services/match.service';
import { match } from '../../match';
import { user } from '../../user';

@Component({
  selector: 'app-play-page',
  templateUrl: './play-page.component.html',
  styleUrl: './play-page.component.css'
})
export class PlayPageComponent implements OnInit, OnChanges {
  scenarios: scenario[] | undefined = [];
  multipleChoices: multipleChoice[] | undefined = [];
  singleChoice!: singleChoice | undefined;
  storyId!: number;
  currentScenario!: scenario | undefined;
  userAnswer!: string;
  dropMap: Map<number, string | undefined> = new Map();
  requiredMap: Map<number, string | undefined> = new Map();

  constructor(
    private localStorageService: LocalStorageService,
    private scenarioService: ScenarioService,
    private multipleChoiceService: MultipleChoiceService,
    private singleChoiceService: SingleChoiceService,
    private storyObjectService: storyObjectService,
    private dropService: DropService,
    private requiredService: RequiredService,
    private matchService: MatchService,
    private inventoryService: InventoryService
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log("richiamo al meotod ngOnchanges");
  }

  async ngOnInit() {
    await this.loadCurrentStory();
    console.log("richiamo al meotod ngOnInit");
  }

  async loadCurrentStory() {
    const currentStory = this.localStorageService.getItem('currentStory');
    if (currentStory) {
      this.storyId = currentStory.id;

      if (this.matchService.getIsFirstMatch()) {
        const initialScenario = await this.scenarioService.getFirstScenario(this.storyId).toPromise();
        this.loadInitialScenario(this.storyId, initialScenario);
      } else {
        const currentScenario=this.localStorageService.getItem("currentScenario");
        this.loadUserChoice(currentScenario.id);
      }

    } else {
      console.error('Nessuna storia corrente trovata nel localStorage');
    }
  }


  async loadInitialScenario(storyId: number, scenario: scenario[] | undefined): Promise<void> {
    try {

      if (scenario) {
        this.currentScenario = scenario[0];
        this.scenarioService.changeScenario(this.currentScenario);
        await this.loadDrop(this.currentScenario);
        this.loadScenarioChoices(this.currentScenario);

      } else {
        console.error('Scenario iniziale non trovato per la storia corrente');
      }
    } catch (error) {
      console.error('Errore nel caricamento degli scenari', error);
    }
  }

  async loadScenarioChoices(scenario: scenario) {
    if (scenario) {
      if (scenario.tipo_risposta === 'MULTIPLA') {
        await this.loadMultipleChoices(scenario.id);
      } else if (scenario.tipo_risposta === 'INDOVINELLO') {
        await this.loadSingleChoice(scenario.id);
      }
    }

  }

  async loadMultipleChoices(scenarioId: number): Promise<void> {
    try {
      const multipleChoices = await this.multipleChoiceService.getmultipleChoiceByScenarioId(scenarioId).toPromise();

      if (multipleChoices) {
        this.multipleChoices = multipleChoices;
        this.loadRequired(multipleChoices);
      }

    } catch (error) {
      console.error('Errore nel caricamento delle scelte multiple', error);
    }
  }

  async loadSingleChoice(scenarioId: number): Promise<void> {
    try {
      const singleChoice = await this.singleChoiceService.getSingleChoiceByScenarioId(scenarioId).toPromise();
      this.singleChoice = singleChoice;
    } catch (error) {
      console.error('Errore nel caricamento dell\'indovinello', error);
    }
  }

  async loadUserChoice(idScenarioSuccessivo: number): Promise<void> {
    const currentUser = this.localStorageService.getItem("currentUser");

    this.scenarioService.getScenarioById(idScenarioSuccessivo);

    this.scenarioService.getScenarioById(idScenarioSuccessivo).subscribe(async (nextScenario: scenario) => {

      if (nextScenario) {
        this.currentScenario = nextScenario;
        console.log('Scenario caricato:', this.currentScenario);
        this.localStorageService.setItem('currentScenario', this.currentScenario);
        this.scenarioService.changeScenario(this.currentScenario);
        await this.loadDrop(this.currentScenario);
        // Carica le scelte associate allo scenario corrente
        await this.loadScenarioChoices(this.currentScenario);

        // Verifica se lo scenario ha un drop associato
        const dropName = this.dropMap.get(this.currentScenario.id);
        if (dropName && dropName !== "Nessun Drop") {
          // Mostra un alert se lo scenario ha un oggetto associato
          alert(`Complimenti! Hai ottenuto l'oggetto: ${dropName}.`);//DA MODIFICARE LA POSIZIONE DELL'ALERT(DA FARE)
        }

        this.updateMatch(currentUser, this.currentScenario);

      } else {
        console.error('Scenario successivo non trovato');
      }
    });
  }

  updateMatch(currentUser: user, nextScenario: scenario) {
    const currentMatch = this.localStorageService.getItem("currentMatch");

    const newMatchData: match = {
      id: currentMatch.id,
      id_storia: this.storyId,
      id_utente: currentUser?.id,
      id_scenario_corrente: nextScenario.id
    };

    this.matchService.updateMatch(newMatchData).subscribe({
      next: (updatedMatch) => {

        this.matchService.changeMatch(newMatchData);
        console.log("Partita aggiornata con successo:", updatedMatch);
      },
      error: (error) => {

        console.error("Errore durante l'aggiornamento della partita:", error);
      }
    });


  }

  //mettere l'aggiunta dell'oggetto nell'inventario(non sono riuscito a fare in modo che )
  async loadDrop(scenario: scenario): Promise<void> {
    try {
      const drop = await this.dropService.getDropByScenarioId(scenario.id).toPromise();
      if (!drop) {
        this.dropMap.set(scenario.id, "Nessun Drop");
        return;
      }

      const currentMatch = this.matchService.getCurrentMatch();
      if (!currentMatch) {
        console.log("Nessuna partita corrente trovata.");
        return;
      }

      const inventoryData: inventory = {
        id: 0,
        id_partita: currentMatch.id,
        id_oggetto: drop.id_oggetto
      };

      this.saveInventory(inventoryData);

      // Aggiorna la mappa dei drop con il nome dell'oggetto
      const storyObject = await this.storyObjectService.getStoryObject(drop.id_oggetto).toPromise();
      this.dropMap.set(scenario.id, storyObject ? storyObject.nome : "Oggetto non trovato");
    } catch (error) {
      console.error("Errore durante il caricamento del drop per lo scenario " + scenario.id, error);
    }
  }

  saveInventory(inventory: inventory): void {

    this.inventoryService.addinventory(inventory).subscribe(
      (response: inventory) => {
        this.inventoryService.changeinventory(response);
        console.log("Oggetto aggiunto all'inventario!");

      },
      (error: HttpErrorResponse) => {
        //gestire i vari codici di errore che arrivano da parte della richiesta http(da fare)
        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert("c'è stato un errore:" + error.error.message);
        }
      }
    );

  }

  async loadRequired(multipleChoices: multipleChoice[]): Promise<void> {
    for (const multipleChoice of multipleChoices) {
      try {
        const required = await this.requiredService.getRequiredByScenarioId(multipleChoice.id).toPromise();
        if (required) {
          try {
            const storyObject = await this.storyObjectService.getStoryObject(required.id_oggetto).toPromise();
            if (storyObject) {
              this.requiredMap.set(multipleChoice.id, storyObject.nome);
            }

          } catch (error) {
            console.log("Errore nel caricamento del nome dell'oggetto da required: " + error);
          }
        } else {
          this.requiredMap.set(multipleChoice.id, "Nessun oggetto richiesto");
        }
        //console.log("avvenuto caricamento del required della scelta: " + multipleChoice.id);
      } catch (error) {
        console.log("Errore nel caricamento dei required per la scelta " + multipleChoice.id + ": " + error);
      }
    }
  }

  // Metodo per gestire l'invio della risposta negli indovinelli 
  submitUserAnswer(): void {
    if (this.singleChoice) {
      // Verifica l'esito della risposta 
      const isCorrect = this.userAnswer === this.singleChoice.risposta;
      alert(`La risposta inserita è ${isCorrect ? 'corretta' : 'sbagliata'}.`);

      // in base all'esito viene preso l'id dello scenario
      const nextScenarioId = isCorrect ? this.singleChoice.id_scenario_risposta_corretta : this.singleChoice.id_scenario_risposta_sbagliata;
      this.loadUserChoice(nextScenarioId);
    }

    //resetto a stringa vuota così nei successivi indovinelli non si vede la ripsosta data precedentemente
    this.userAnswer = '';
  }
  
}