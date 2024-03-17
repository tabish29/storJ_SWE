import { Component } from '@angular/core';
import { SingleChoiceService } from '../../services/single-choice.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ScenarioService } from '../../services/scenario.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { scenario } from '../../scenario';
import { singleChoice } from '../../singleChoice';

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
  idScenarioRispostaCorretta = 0;
  idScenarioRispostaSbagliata = 0;
  storyScenarios: scenario[] = [];


  constructor(private http: HttpClient, private singleChoiceService: SingleChoiceService, private scenarioService: ScenarioService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.loadScenarioId();
    this.loadStoryScenarios(this.idScenario);

  }

  loadScenarioId() {
    const currentScenario = this.localStorageService.getItem('currentScenario');
    if (currentScenario) {
      this.idScenario = currentScenario.id;
    } else {
      alert('id scenario non trovato');
    }
  }


  loadStoryScenarios(idScenario:number) {
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
        alert("singleChoice creato con successo!")

        this.router.navigateByUrl('/createStory');

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

    this.saveSingleChoice(singleChoiceData);
  }


}
