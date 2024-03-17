import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { MultipleChoiceService } from '../../services/multiple-choice.service';
import { multipleChoice } from '../../multipleChoice';

@Component({
  selector: 'app-multiple-choice',
  templateUrl: './multiple-choice.component.html',
  styleUrl: './multiple-choice.component.css'
})
export class MultipleChoiceComponent {
    
multipleChoice: multipleChoice[] = [];
scenarioId!: number;

constructor(private multipleChoiceService: MultipleChoiceService, private router: Router, private localStorageService: LocalStorageService) { }

ngOnInit(): void {
  const currentscenario = this.localStorageService.getItem('currentScenario');
  this.scenarioId = currentscenario.id;
  this.loadMultipleChoices();
}

loadMultipleChoices(): void {
  this.multipleChoiceService.getmultipleChoiceByScenarioId(this.scenarioId).subscribe(
    (multpliChoices: multipleChoice[]) => {
      this.multipleChoice = multpliChoices;
    },
    error => {
      console.error('Errore nel caricamento delle scelte', error);
    }
  );
}

removemultipleChoice(multpliChoiceId: number): void {
  this.multipleChoiceService.deletemultipleChoice(multpliChoiceId);
  this.loadMultipleChoices();
  location.reload(); 
}

}
