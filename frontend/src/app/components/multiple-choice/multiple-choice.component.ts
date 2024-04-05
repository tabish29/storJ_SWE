import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { MultipleChoiceService } from '../../services/multiple-choice.service';
import { multipleChoice } from '../../multipleChoice';
import { RequiredService } from '../../services/required.service';
import { storyObjectService } from '../../services/story-object.service';
import { StoryService } from '../../services/story.service';
import { ScenarioService } from '../../services/scenario.service';

@Component({
  selector: 'app-multiple-choice',
  templateUrl: './multiple-choice.component.html',
  styleUrl: './multiple-choice.component.css'
})
export class MultipleChoiceComponent {

  multipleChoices: multipleChoice[] = [];
  scenarioId!: number;
  requiredMap: Map<number, string | undefined> = new Map();
  scenarioTextMap: Map<number, string | undefined> = new Map();
  isInTextEditMode!: boolean;


  constructor(private multipleChoiceService: MultipleChoiceService, private requiredService: RequiredService, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService, private scenarioService: ScenarioService) { }

  async ngOnInit(): Promise<void> {
    const currentscenario = this.localStorageService.getItem('currentScenario');
    this.scenarioId = currentscenario.id;
    await this.loadMultipleChoices()
    await this.loadRequired(this.multipleChoices);
    await this.loadScenarioText(this.multipleChoices);
    this.isInTextEditMode = this.storyService.isStoryCompleted();
  }


  async loadMultipleChoices(): Promise<void> {
    try {
      const multipleChoices = await (this.multipleChoiceService.getMultipleChoiceByScenarioId(this.scenarioId)).toPromise();
      if (multipleChoices) {
        // Ordino in base all'Id delle sclete multiple
        this.multipleChoices = multipleChoices.sort((a, b) => a.id - b.id);
      }
    } catch (error) {
      console.log('Errore nel caricamento dei scenari', error);
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
      } catch (error) {
        console.log("Errore nel caricamento dei required per la scelta " + multipleChoice.id + ": " + error);
      }
    }
  }

  async loadScenarioText(multipleChoices: multipleChoice[]): Promise<void> {
    for (const multipleChoice of multipleChoices) {
      try {
        const scenario = await this.scenarioService.getScenarioById(multipleChoice.id_scenario_successivo).toPromise();

        if (scenario) {
          this.scenarioTextMap.set(multipleChoice.id, scenario.testo);
        }

      } catch (error) {
        console.log("Errore nel caricamento dello scenario per la scelta " + multipleChoice.id + ": " + error);
      }
    }
  }

  removeMultipleChoice(multpliChoiceId: number): void {
    this.multipleChoiceService.deleteMultipleChoice(multpliChoiceId);
    this.loadMultipleChoices();
    location.reload();
  }

  editMultipleChoicheText(newMultipleChoice: multipleChoice) {
    this.changeMultipleChoiche(newMultipleChoice);
    this.router.navigateByUrl("/formMultipleChoice");
  }

  changeMultipleChoiche(newMultipleChoice: multipleChoice) {
    this.multipleChoiceService.changeMultipleChoice(newMultipleChoice);
  }


}