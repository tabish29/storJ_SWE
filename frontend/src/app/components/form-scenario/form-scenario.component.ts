import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { scenarioType } from '../../scenarioType';
import { responseType } from '../../responseType';
import { LocalStorageService } from '../../services/local-storage.service';

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

  constructor(private http: HttpClient, private scenarioService: ScenarioService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.loadStoryId();

  }

  loadStoryId() {
    const currentStory = this.localStorageService.getItem('currentStory');
    if (currentStory) {
      this.idStoria = currentStory.id;
    } else {
      alert('id storia non trovato');
    }
  }


  public savescenario(scenario: scenario): void {

    this.scenarioService.addscenario(scenario).subscribe(
      (response: scenario) => {
        this.scenarioService.changescenario(response);
        alert("Scenario creato con successo!")

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
