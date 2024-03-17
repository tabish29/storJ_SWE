import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { MultipleChoiceService } from '../../services/multiple-choice.service';
import { multipleChoice } from '../../multipleChoice';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';


@Component({
  selector: 'app-form-multiple-choice',
  templateUrl: './form-multiple-choice.component.html',
  styleUrl: './form-multiple-choice.component.css'
})
export class FormMultipleChoiceComponent {
//inserire le variabili che servono nel form della creazione della multiplechoice 
idScenario=-1;
testo='';
idScenarioSuccessivo=-1;
storyScenarios: scenario[] = [];

constructor(private http: HttpClient, private multipleChoiceService: MultipleChoiceService,private scenarioService:ScenarioService,private router: Router, private localStorageService: LocalStorageService) { }

ngOnInit(): void {
  this.loadScenarioId();
  this.loadStoryScenarios();

}

loadScenarioId() {
  const currentScenario = this.localStorageService.getItem('currentScenario');
  if (currentScenario) {
    this.idScenario = currentScenario.id;
  } else {
    alert('id scenario non trovato');
  }
}


loadStoryScenarios() {
  const currentStoryId = this.localStorageService.getItem('currentStory')?.id;
  if (currentStoryId) {
    this.scenarioService.getScenarioByStoryId(currentStoryId).subscribe(scenarios => {
      this.storyScenarios = scenarios;
    }, error => console.error(error));
  }
}


public savemultiplechoice(multiplechoice: multipleChoice): void {

  this.multipleChoiceService.addmultipleChoice(multiplechoice).subscribe(
    (response: multipleChoice) => {
      this.multipleChoiceService.changemultipleChoice(response);
      alert("multiplechoice creato con successo!")

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
  const multiplechoiceData: multipleChoice = {
    id: 0,
    id_scenario: this.idScenario,
    testo: this.testo,
    id_scenario_successivo: this.idScenarioSuccessivo
  };

  this.savemultiplechoice(multiplechoiceData);
}

}
