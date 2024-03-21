import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { singleChoice } from '../../singleChoice';
import { SingleChoiceService } from '../../services/single-choice.service';


@Component({
  selector: 'app-single-choice',
  templateUrl: './single-choice.component.html',
  styleUrl: './single-choice.component.css'
})
export class SingleChoiceComponent {

  singleChoice!: singleChoice;
  scenarioId!: number;

  constructor(private singleChoiceService: SingleChoiceService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    const currentscenario = this.localStorageService.getItem('currentScenario');
    this.scenarioId = currentscenario.id;
    this.loadSingleChoice();
  }

  loadSingleChoice(): void {
    this.singleChoiceService.getSingleChoiceByScenarioId(this.scenarioId).subscribe(
      (singleChoice: singleChoice) => {
        this.singleChoice = singleChoice;
       
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


}
