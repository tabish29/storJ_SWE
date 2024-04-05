import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA,MatDialogRef } from '@angular/material/dialog';
import { ScenarioService } from '../../services/scenario.service';
import { SingleChoiceService } from '../../services/single-choice.service';
import { MultipleChoiceService } from '../../services/multiple-choice.service';
import { scenario } from '../../scenario';
import { multipleChoice } from '../../multipleChoice';
import { singleChoice } from '../../singleChoice';
import { story } from '../../story';


@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrl: './popup.component.css'
})
export class PopupComponent {
  showPreview!:boolean; 
  story!:story;
  currentScenario?: scenario;
  multipleChoices?: multipleChoice[];
  singleChoice?: singleChoice;

  constructor(
    public dialogRef: MatDialogRef<PopupComponent>,
    private scenarioService: ScenarioService,
    private singleChoiceService: SingleChoiceService,
    private multipleChoiceService: MultipleChoiceService,
    @Inject(MAT_DIALOG_DATA) public data: any 
  ) {
    this.showPreview = data.showPreview;
    this.story=data.story;
  }

  ngOnInit(): void {
    if (this.showPreview) {
      const storyId = this.story.id; 
      this.loadInitialScenario(storyId);
    }
  }

  async loadInitialScenario(storyId: number) {
    try {
      const initialScenario = await this.scenarioService.getFirstScenario(storyId).toPromise();
      if (initialScenario && initialScenario.length > 0) {
        this.currentScenario=initialScenario[0];
        this.loadScenarioChoices(initialScenario[0].id);
      }
    } catch (error) {
      console.error('Errore nel caricamento dello scenario iniziale', error);
    }
  }

  async loadScenarioChoices(scenarioId: number) {
    try {
      if (this.currentScenario?.tipo_risposta === 'MULTIPLA') {
        this.multipleChoices = await this.multipleChoiceService.getMultipleChoiceByScenarioId(scenarioId).toPromise();
      } else if (this.currentScenario?.tipo_risposta === 'INDOVINELLO') {
        this.singleChoice = await this.singleChoiceService.getSingleChoiceByScenarioId(scenarioId).toPromise();
      }
    } catch (error) {
      console.error('Errore nel caricamento delle scelte per lo scenario', error);
    }
  }

  onClose(): void {
    this.dialogRef.close(); 
  }
}