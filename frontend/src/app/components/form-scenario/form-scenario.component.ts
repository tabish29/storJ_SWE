import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { storyObject } from '../../storyObject';
import { drop } from '../../drop';
import { scenarioType } from '../../scenarioType';
import { responseType } from '../../responseType';
import { LocalStorageService } from '../../services/local-storage.service';
import { storyObjectService } from '../../services/story-object.service';
import { DropService } from '../../services/drop.service';
import { StoryService } from '../../services/story.service';

@Component({
  selector: 'app-form-scenario',
  templateUrl: './form-scenario.component.html',
  styleUrl: './form-scenario.component.css'
})
export class FormScenarioComponent implements OnInit {

  idStoria = -1;
  testo = '';
  tipoRisposta!: responseType;
  tipoScenario!: scenarioType;
  storyObjects: storyObject[] = [];
  selectedObjectId: number = -1;
  isInTextEditMode!: boolean;
  currentScenario!: scenario | null;

  constructor(private http: HttpClient, private scenarioService: ScenarioService, private storyObjectService: storyObjectService, private dropService: DropService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService) { }

  ngOnInit(): void {
    this.loadStoryId();
    this.loadStoryObjects();
    this.isInTextEditMode = this.storyService.isStoryCompleted();
    this.loadCurrentscenario();
  }

  loadCurrentscenario(): void {
    this.currentScenario = this.localStorageService.getItem("currentScenario");
  }

  loadStoryId() {
    const currentStory = this.localStorageService.getItem('currentStory');
    if (currentStory) {
      this.idStoria = currentStory.id;
    } else {
      alert('id storia non trovato');
    }
  }

  loadStoryObjects() {
    const currentStoryId = this.localStorageService.getItem('currentStory')?.id;
    if (currentStoryId) {
      this.storyObjectService.getStoryObjectByStoryId(currentStoryId).subscribe(
        (objects) => {
          this.storyObjects = objects;
        },
        (error) => alert('Errore nel caricamento degli oggetti')
      );
    }
  }

  saveDrop(drop: drop) {
    this.dropService.addDrop(drop).subscribe(
      (response: drop) => {

        this.dropService.changeDrop(response);
        console.log("Drop creato con successo!");
      },
      (error: HttpErrorResponse) => {
        console.log("c'è stato un errore nella crezione del drop" + error.error.message)
      }
    );
  }

  saveScenario(scenario: scenario): void {
    this.scenarioService.addScenario(scenario).subscribe(
      (response: scenario) => {
        this.scenarioService.changeScenario(response);

        if (this.selectedObjectId != -1) {
          const objDrop: drop = {
            id: 0,
            id_scenario: response.id,
            id_oggetto: this.selectedObjectId
          };
          this.saveDrop(objDrop);

        }

        this.router.navigateByUrl('/createStory').then(() => {

          window.location.reload();
        });

      },
      (error: HttpErrorResponse) => {
        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert("c'è stato un errore:" + error.error.message + " id della storia salvata" + this.idStoria);
        }
      }
    );
  }

  onSubmit() {
    if (this.tipoScenario === 'FINALE') {
      this.tipoRisposta = responseType.MULTIPLA;
    }

    const scenarioData: scenario = {
      id: 0,
      id_storia: this.idStoria,
      testo: this.testo,
      tipo_risposta: this.tipoRisposta,
      tipo_scenario: this.tipoScenario
    };

    if (this.testo == '' || this.tipoRisposta == undefined || this.tipoScenario == undefined) {
      alert("Inserisci tutti i campi obbligatori (*)");
    } else {
      this.saveScenario(scenarioData);
    }

  }

  onScenarioTextChange(newTesto: string): void {
    if (this.currentScenario) {
      this.currentScenario.testo = newTesto; // Aggiorna il testo dello scenario corrente
    }
    this.testo = newTesto; // Aggiorna la variabile testo del componente
  }

  isUpdated(): boolean {
    return !this.testo.trim();
  }

  updateScenarioText() {

    if (this.currentScenario) {
      const newscenarioData: scenario = {
        id: this.currentScenario.id,
        id_storia: this.idStoria,
        testo: this.testo,
        tipo_risposta: this.currentScenario.tipo_risposta,
        tipo_scenario: this.currentScenario.tipo_scenario
      };

      this.scenarioService.updateScenario(newscenarioData).subscribe({
        next: (updatedScenario) => {
          this.scenarioService.changeScenario(newscenarioData);
          console.log("Scenario aggiornato con successo:", updatedScenario);

          this.router.navigateByUrl('/createStory').then(() => {
            window.location.reload();
          });
        },
        error: (error) => {
          console.error("Errore durante l'aggiornamento dello scenario:", error);
        }
      });
    }
  }

}