import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { LocalStorageService } from '../../services/local-storage.service';
import { DropService } from '../../services/drop.service';
import { storyObjectService } from '../../services/story-object.service';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrl: './create-story.component.css'
})
export class CreateStoryComponent {

  //Da modificare fare in modo che vengano visualizzate solo le storie dell'utente loggato
  scenarios: scenario[]=[];
  storyId!: number;
  dropMap: Map<number, string | undefined>=new Map();
  dropName!: string;

  constructor(private scenarioService: ScenarioService, private dropService: DropService, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    // Recupera l'ID della storia corrente dal localStorage
    const currentStory = this.localStorageService.getItem('currentStory');
    this.storyId = currentStory.id;
    this.loadScenarios();
  }

  loadScenarios(): void {
    this.scenarioService.getScenarioByStoryId(this.storyId).subscribe(
      (scenarios: scenario[]) => {
        this.scenarios = scenarios;
        console.log("lunghezza dell'array scenari scenario "+scenarios.length);
        this.loadDrop(scenarios);
      },
      error => {
        console.log('Errore nel caricamento dei scenari');
      }
    );
  }

  async loadDrop(scenarios: scenario[]): Promise<void> {
    for (const scenario of scenarios) {
        try {
            const drop = await this.dropService.getDropByScenarioId(scenario.id).toPromise();
            if (drop) {
                try {
                    const storyObject = await this.storyObjectService.getStoryObject(drop.id_oggetto).toPromise();
                    if(storyObject){
                    this.dropMap.set(scenario.id, storyObject.nome);
                  }
                    
                } catch (error) {
                    console.error("Errore nel caricamento del nome dell'oggetto da drop", error);
                }
            } else {
                this.dropMap.set(scenario.id, "Nessun Drop");
            }
        } catch (error) {
            console.error(`Errore nel caricamento dei drop per lo scenario ${scenario.id}`, error);
        }
    }
}

  scenarioHasDrop(idScenario: number): boolean {
      // Per visualizzare la Map come array di [key, value]
      console.log(Object.fromEntries(this.dropMap));
   
    if(this.dropMap.has(idScenario)){
      console.log(idScenario+"esiste nella mappa");

    }else{
      console.log(idScenario+"non esiste nella mappa");
    }
    return this.dropMap.has(idScenario);

  }

  removeScenario(scenarioId: number): void {
    this.scenarioService.deleteScenario(scenarioId);
    this.loadScenarios();
    location.reload(); //per fare il refresh dell'applicazione
  }

  redirectToScenarioType(scenario: scenario, type: string): void {
    this.changeScenario(scenario);
    if (type === 'MULTIPLA') {
      this.router.navigateByUrl('/multiplechoice');
    } else if (type === 'INDOVINELLO') {
      this.router.navigateByUrl('/singlechoice');
    } else {
      console.warn('Tipo di risposta non riconosciuto:', type);
    }
  }

  changeScenario(newscenario: scenario) {
    this.scenarioService.changescenario(newscenario);
  }

}