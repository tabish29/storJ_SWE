import { Component } from '@angular/core';
import { SingleChoiceService } from '../../services/single-choice.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ScenarioService } from '../../services/scenario.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { scenario } from '../../scenario';
import { singleChoice } from '../../singleChoice';
import { storyObject } from '../../storyObject';
import { storyObjectService } from '../../services/story-object.service';
import { StoryService } from '../../services/story.service';

@Component({
  selector: 'app-form-single-choice',
  templateUrl: './form-single-choice.component.html',
  styleUrl: './form-single-choice.component.css'
})
export class FormSingleChoiceComponent {

  //inserire le variabili che servono nel form della creazione della singleChoice 
  idScenario = 0;
  testo = '';
  risposta = '';
  idScenarioRispostaCorretta = -1;
  idScenarioRispostaSbagliata = -1;
  storyScenarios: scenario[] = [];
  storyObjects: storyObject[] = [];
  selectedObjectId: number = -1;
  isInTextEditMode!: boolean;
  currentSingleChoiche!: singleChoice | null;
  isBottonDisabled: boolean = true; //per il bottone dell'aggironameto dei campi testuali


  constructor(private http: HttpClient, private singleChoiceService: SingleChoiceService, private scenarioService: ScenarioService, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService) { }

  ngOnInit(): void {
    this.loadScenarioId();
    this.loadStoryScenarios(this.idScenario);
    this.loadStoryObjects();
    this.isInTextEditMode = this.storyService.isStoryCompleted();
    this.loadCurrentSingleChoice();

  }

  loadCurrentSingleChoice(): void {
    this.currentSingleChoiche = this.localStorageService.getItem("currentSingleChoice");

  }

  loadScenarioId() {
    const currentScenario = this.localStorageService.getItem('currentScenario');
    if (currentScenario) {
      this.idScenario = currentScenario.id;
    } else {
      alert('id scenario non trovato');
    }
  }

  loadStoryObjects() {
    const currentStoryId = this.localStorageService.getItem('currentStory')?.id;
    if (currentStoryId) {
      this.storyObjectService.getStoryObjectByStoryId(currentStoryId).subscribe(
        (objects) => {
          this.storyObjects = objects;
        },
        (error) => alert('Errore nel caricamento degli oggetti')//(Da modificare) 
      );
    }
  }


  loadStoryScenarios(idScenario: number) {
    const currentStoryId = this.localStorageService.getItem('currentStory').id;
    if (currentStoryId) {
      this.scenarioService.getScenarioByStoryId(currentStoryId).subscribe(scenarios => {
        this.storyScenarios = scenarios.filter(scenario => scenario.id !== idScenario);
      }, error => console.error(error));
    }
  }


  public saveSingleChoice(singleChoice: singleChoice): void {

    this.singleChoiceService.addSingleChoice(singleChoice).subscribe(
      (response: singleChoice) => {
        this.singleChoiceService.changeSingleChoice(response);
        this.singleChoiceService.setIsChoiceCreated(true);
        alert("singleChoice creato con successo!");

        this.router.navigateByUrl('/singlechoice');

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

  onSubmit() {
    //decidere se servono le infomazioni che vengono aggiunte direttamente dal backend(da decidere)
    const singleChoiceData: singleChoice = {
      id: 0,
      id_scenario: this.idScenario,
      testo: this.testo,
      risposta: this.risposta,
      id_scenario_risposta_corretta: this.idScenarioRispostaCorretta,
      id_scenario_risposta_sbagliata: this.idScenarioRispostaSbagliata
    };

    if (this.testo == ' ' || this.idScenarioRispostaCorretta == -1 || this.idScenarioRispostaSbagliata == -1) {
      alert("Inserisci tutti i campi obbligatori (*)");
    } else {
      this.saveSingleChoice(singleChoiceData);
    }

   
  }

  onTextChange(newTesto: string): void {
    if (this.currentSingleChoiche) {
      this.currentSingleChoiche.testo = newTesto;
      //console.log("sono dentro on textchange");
    }
    this.testo = newTesto;
    this.isBottonDisabled = false;
    //console.log("nuovo valore di testo: "+this.testo);
  }

  onAnswerChange(newAnswer: string): void {
    if (this.currentSingleChoiche) {
      this.currentSingleChoiche.risposta = newAnswer;
      //console.log("sono dentro on answerchange");
      this.testo = this.currentSingleChoiche.testo;
    }
    this.risposta = newAnswer;
    this.isBottonDisabled = false;
  }

  // metodo per obbligare l'utente a modificare almeno uno dei due campi
  isUpdated(): boolean {
    console.log();
    return this.isBottonDisabled;
  }

  updateSingleChoicheText() {

    if (this.currentSingleChoiche) {
      const newSingleChoicheData: singleChoice = {
        id: this.currentSingleChoiche.id,
        id_scenario: this.idScenario,
        testo: this.testo,
        risposta: this.risposta,
        id_scenario_risposta_corretta: this.currentSingleChoiche.id_scenario_risposta_corretta,
        id_scenario_risposta_sbagliata: this.currentSingleChoiche.id_scenario_risposta_sbagliata
      };

      console.log("i nuovi dati del'indovinello: " + JSON.stringify(newSingleChoicheData));


      this.singleChoiceService.updateSingleChoice(newSingleChoicheData).subscribe({
        next: (updatedSingleChoice) => {

          this.singleChoiceService.changeSingleChoice(newSingleChoicheData);
          console.log("Indovinello aggiornata con successo:", updatedSingleChoice);

          this.router.navigateByUrl('/singlechoice').then(() => {
            // Ricarica la pagina dopo la navigazione(da implementare anche nello scenario,nel drop e nel required)
            window.location.reload();
          });
        },
        error: (error) => {

          console.error("Errore durante l'aggiornamento della scelta multipla:", error);
        }
      });
    }
  }

}