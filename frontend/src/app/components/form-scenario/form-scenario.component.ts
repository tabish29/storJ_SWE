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

@Component({
  selector: 'app-form-scenario',
  templateUrl: './form-scenario.component.html',
  styleUrl: './form-scenario.component.css'
})
export class FormScenarioComponent implements OnInit {
  //inserire le variabili che servono nel form della creazione dello scenario 

  idStoria = -1;
  testo = '';
  tipoRisposta!: responseType;
  tipoScenario!: scenarioType;
  storyObjects:storyObject[]=[];
  selectedObjectId: number= -1; 

  constructor(private http: HttpClient, private scenarioService: ScenarioService,private storyObjectService:storyObjectService,private dropService:DropService,private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.loadStoryId();
    this.loadStoryObjects();

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
        (error) => alert('Errore nel caricamento degli oggetti')//(Da modificare) 
      );
    }
  }

  public saveDrop(drop:drop){

    this.dropService.addDrop(drop).subscribe(
      (response: drop) => {
        this.dropService.changeDrop(response);
        console.log("Drop creato con successo!");

      },
      (error: HttpErrorResponse) => {
        //gestire i vari codici di errore che arrivano da parte della richiesta http(da fare)
       console.log("c'è stato un errore nella crezione del drop"+error.error.message)
      }
    );
  }

  public savescenario(scenario: scenario): void {

    this.scenarioService.addscenario(scenario).subscribe(
      (response: scenario) => {
        this.scenarioService.changescenario(response);
        alert("Scenario creato con successo!");

        if(this.selectedObjectId != -1){
          const objDrop: drop={
            id: 0,
            id_scenario: response.id,//id dello scenario appena creato
            id_oggetto: this.selectedObjectId
          };
          this.saveDrop(objDrop);
    
        }

        this.router.navigateByUrl('/createStory');

      },
      (error: HttpErrorResponse) => {
        //gestire i vari codici di errore che arrivano da parte della richiesta http(da fare)
        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert("c'è stato un errore:" + error.error.message + " id della storia salvata" + this.idStoria);
        }
      }
    );
  }

  onSubmit() {
    //mettere anche il salvataggio di drop nel caso venisse creato 
    //decidere se servono le infomazioni che vengono aggiunte direttamente dal backend(da decidere)
    const scenarioData: scenario = {
      id: 0,
      id_storia: this.idStoria,
      testo: this.testo,
      tipo_risposta: this.tipoRisposta,
      tipo_scenario: this.tipoScenario
    };

    this.savescenario(scenarioData);
  }



}
