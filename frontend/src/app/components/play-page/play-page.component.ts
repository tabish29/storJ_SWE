import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-play-page',
  templateUrl: './play-page.component.html',
  styleUrl: './play-page.component.css'
})
export class PlayPageComponent implements OnInit {
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
    private requiredService: RequiredService
  ) { }

  async ngOnInit() {
    await this.loadCurrentStory();
  }

  async loadCurrentStory() {
    const currentStory = this.localStorageService.getItem('currentStory');
    if (currentStory) {
      this.storyId = currentStory.id;
      await this.loadScenarios(this.storyId);
    } else {
      console.error('Nessuna storia corrente trovata nel localStorage');
    }
  }

  async loadScenarios(storyId: number): Promise<void> {
    try {
      const scenarios = await this.scenarioService.getScenarioByStoryId(storyId).toPromise();

      if (scenarios) {
        this.scenarios = scenarios;
        this.currentScenario = scenarios.find(scenario => scenario.tipo_scenario === 'INIZIALE');
        this.loadDrop(scenarios);
      }

      //(da fare )creare un  metodo che abbia questa logica di caricamento degli scenari
      if (this.currentScenario) {
        if (this.currentScenario.tipo_risposta === 'MULTIPLA') {
          await this.loadMultipleChoices(this.currentScenario.id);
        } else if (this.currentScenario.tipo_risposta === 'INDOVINELLO') {
          await this.loadSingleChoice(this.currentScenario.id);
        }
      }
    } catch (error) {
      console.error('Errore nel caricamento degli scenari', error);
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

  async loadChoice(idScenarioSuccessivo: number): Promise<void> {
    const nextScenario = this.scenarios?.find(scenario => scenario.id === idScenarioSuccessivo);

    if (nextScenario) {
      this.currentScenario = nextScenario;
      console.log('Scenario successivo caricato:', this.currentScenario);


      // Logica aggiuntiva per verificare il drop associato
      const dropName = this.dropMap.get(this.currentScenario.id);
      if (dropName && dropName !== "Nessun Drop") {
        alert(`Complimenti! Hai ottenuto l'oggetto: ${dropName}.`);
      }

      //(da fare )creare un  metodo che abbia questa logica di caricamento degli scenari(stessa logica di sopra nel metodo loadScenarios)
      if (this.currentScenario.tipo_risposta === 'MULTIPLA') {
        await this.loadMultipleChoices(this.currentScenario.id);
      } else if (this.currentScenario.tipo_risposta === 'INDOVINELLO') {
        await this.loadSingleChoice(this.currentScenario.id);
      }
    } else {
      console.error('Scenario successivo non trovato');
    }
  }

  async loadDrop(scenarios: scenario[]): Promise<void> {
    for (const scenario of scenarios) {
      try {
        const drop = await this.dropService.getDropByScenarioId(scenario.id).toPromise();
        if (drop) {
          try {
            const storyObject = await this.storyObjectService.getStoryObject(drop.id_oggetto).toPromise();
            if (storyObject) {
              this.dropMap.set(scenario.id, storyObject.nome);
            }

          } catch (error) {
            console.log("Errore nel caricamento del nome dell'oggetto da drop: " + error);
          }
        } else {
          this.dropMap.set(scenario.id, "Nessun Drop");
        }
        console.log("avvenuto caricamento del drop dello scenariio: " + scenario.id);
      } catch (error) {
        console.log("Errore nel caricamento dei drop per lo scenario " + scenario.id + ": " + error);
      }
    }
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
        console.log("avvenuto caricamento del required della scelta: " + multipleChoice.id);
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
      this.loadChoice(nextScenarioId);
    }

    //resetto a stringa vuota così nei successivi indovinelli non si vede la ripsosta data precedentemente
    this.userAnswer = '';
  }


}
