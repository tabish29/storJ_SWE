import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { LocalStorageService } from '../../services/local-storage.service';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrl: './create-story.component.css'
})
export class CreateStoryComponent{
  
//Da modificare fare in modo che vengano visualizzate solo le storie dell'utente loggato
scenarios: scenario[] = [];
storyId!: number;

constructor(private scenarioService: ScenarioService, private router: Router, private localStorageService: LocalStorageService) { }

ngOnInit(): void {
  // Recupera l'ID della storia corrente dal localStorage
  const currentStory = this.localStorageService.getItem('currentStory');
  this.storyId = currentStory.id;
  this.loadscenarios();
}

loadscenarios(): void {
  this.scenarioService.getScenarioByStoryId(this.storyId).subscribe(
    (scenarios: scenario[]) => {
      this.scenarios = scenarios;
    },
    error => {
      console.error('Errore nel caricamento delle storie', error);
    }
  );
}

removeScenario(scenarioId: number): void {
  this.scenarioService.deleteScenario(scenarioId);
  this.loadscenarios();
  location.reload(); //per fare il refresh dell'applicazione
}

redirectToScenarioType(scenario:scenario,type: string): void {
  this.changeScenario(scenario);
  if (type === 'MULTIPLA') {
    this.router.navigateByUrl('/multiplechoice');
  } else if (type === 'INDOVINELLO') {
    this.router.navigateByUrl('/singlechoice');
  } else {
    console.warn('Tipo di risposta non riconosciuto:', type);
  }
}

changeScenario(newscenario:scenario){
  this.scenarioService.changescenario(newscenario);
}

}
