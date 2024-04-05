import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { singleChoice } from '../../singleChoice';
import { SingleChoiceService } from '../../services/single-choice.service';
import { StoryService } from '../../services/story.service';
import { ScenarioService } from '../../services/scenario.service';


@Component({
  selector: 'app-single-choice',
  templateUrl: './single-choice.component.html',
  styleUrl: './single-choice.component.css'
})
export class SingleChoiceComponent {

  singleChoice!: singleChoice;
  scenarioId!: number;
  isInTextEditMode!: boolean;
  textCorrectAnswer!: string;
  textWrongAnswer!: string;

  constructor(private singleChoiceService: SingleChoiceService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService, private scenarioService: ScenarioService) { }

  ngOnInit(): void {
    const currentscenario = this.localStorageService.getItem('currentScenario');
    this.scenarioId = currentscenario.id;
    this.loadSingleChoice();
    this.isInTextEditMode = this.storyService.isStoryCompleted();
  }

  loadSingleChoice(): void {
    this.singleChoiceService.getSingleChoiceByScenarioId(this.scenarioId).subscribe(
      (singleChoice: singleChoice) => {
        this.singleChoice = singleChoice;
        this.setTextCorrectAnswer(singleChoice.id_scenario_risposta_corretta);
        this.setTextWrongAnswer(singleChoice.id_scenario_risposta_sbagliata);
      },
      error => {
        console.error('Errore nel caricamento delle scelte', error);
      }
    );
  }

  removeSingleChoice(singleChoiceId: number): void {
    this.singleChoiceService.deleteSingleChoice(singleChoiceId);
    this.singleChoiceService.setIsChoiceCreated(false);
    this.loadSingleChoice();
    location.reload();
  }

  editSingleChoicheText(newSingleChoice: singleChoice) {
    this.changeSingleChoiche(newSingleChoice);
    this.router.navigateByUrl("/formSingleChoice");
  }

  changeSingleChoiche(newSingleChoice: singleChoice) {
    this.singleChoiceService.changeSingleChoice(newSingleChoice);
  }

  setTextCorrectAnswer(idCorrectScenario: number): void {
    this.scenarioService.getScenarioById(idCorrectScenario).subscribe({
      next: (scenario) => {
        this.textCorrectAnswer = scenario.testo;
        console.log("Testo dello scenario corretto impostato con successo:", this.textCorrectAnswer);
      },
      error: (error) => {

        console.error("Errore durante il recupero del testo dello scenario:", error);
      }
    });
  }

  setTextWrongAnswer(idWrongScenario: number) {
    this.scenarioService.getScenarioById(idWrongScenario).subscribe({
      next: (scenario) => {
        this.textWrongAnswer = scenario.testo;
        console.log("Testo dello scenario sbagliato impostato con successo:", this.textWrongAnswer);
      },
      error: (error) => {

        console.error("Errore durante il recupero del testo dello scenario:", error);
      }
    });
  }

}