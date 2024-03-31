import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ScenarioService } from '../../services/scenario.service';
import { scenario } from '../../scenario';
import { LocalStorageService } from '../../services/local-storage.service';
import { DropService } from '../../services/drop.service';
import { storyObjectService } from '../../services/story-object.service';
import { StoryService } from '../../services/story.service';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrl: './create-story.component.css'
})
export class CreateStoryComponent implements OnInit {
  scenarios: scenario[] = [];
  storyId!: number;
  dropMap: Map<number, string | undefined> = new Map();
  isInTextEditMode!: boolean;

  constructor(private scenarioService: ScenarioService, private dropService: DropService, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService) { }

  async ngOnInit(): Promise<void> {
    const currentStory = this.localStorageService.getItem('currentStory');
    this.storyId = currentStory.id;
    await this.loadScenarios();
    await this.loadDrop(this.scenarios);
    this.isInTextEditMode = this.storyService.isStoryCompleted();
  }

  async loadScenarios(): Promise<void> {
    try {
      const scenarios = await (this.scenarioService.getScenarioByStoryId(this.storyId)).toPromise();
      if (scenarios) {
        this.scenarios = scenarios.sort((a, b) => a.id - b.id);
      }
    } catch (error) {
      console.log('Errore nel caricamento dei scenari', error);
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
      } catch (error) {
        console.log("Errore nel caricamento dei drop per lo scenario " + scenario.id + ": " + error);
      }
    }
  }

  removeScenario(scenarioId: number): void {
    this.scenarioService.deleteScenario(scenarioId);
    this.loadScenarios();
    location.reload();
  }

  redirectToScenarioType(scenario: scenario, type: string): void {
    this.changeScenario(scenario);
    if (type === 'MULTIPLA') {
      this.router.navigateByUrl('/multiplechoice');
    } else if (type === 'INDOVINELLO') {
      this.router.navigateByUrl('/singlechoice');
    } else {
      console.log('Tipo di risposta non riconosciuto: ' + type);
    }
  }

  editScenarioText(newScenario: scenario) {
    this.changeScenario(newScenario);
    this.router.navigateByUrl("/formScenario");
  }

  changeScenario(newscenario: scenario) {
    this.scenarioService.changeScenario(newscenario);
  }
}