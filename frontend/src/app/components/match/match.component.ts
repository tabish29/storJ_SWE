import { Component } from '@angular/core';
import { match } from '../../match';
import { MatchService } from '../../services/match.service';
import { UserService } from '../../services/userservice';
import { StoryService } from '../../services/story.service';
import { Router } from '@angular/router';import { LocalStorageService } from '../../services/local-storage.service';
;

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrl: './match.component.css'
})
export class MatchComponent {
  userMatches: match[] = [];

  constructor(private router:Router,private matchService: MatchService, private userService: UserService, private storyService: StoryService,private localStorageService:LocalStorageService) { }

  ngOnInit() {
    this.loadUserMatches();
  }

  loadUserMatches() {
    const userId = this.userService.getCurrentUser()?.id;
    if (userId) {
      this.matchService.getMatchByUser(userId).subscribe({
        next: (matches) => {
          this.userMatches = matches;
        },
        error: (error) => {
          console.error('Errore durante il caricamento delle partite dell\'utente:', error);
        }
      });
    }
  }

  resumeMatch(match: match) {
    this.storyService.getStoriesByStoryID(match.id_storia).subscribe({
      next: (response) => {
        // Qui impostiamo la storia corrente sul servizio di gestione delle storie
        this.storyService.changeStory(response);
        console.log(`Riprendi la partita con ID: ${match.id}`);
        this.matchService.setIsFirstMatch(false);
        this.localStorageService.setItem('currentMatch', match);
        this.localStorageService.setItem('currentScenarioID', match.id_scenario_corrente);
        // Reindirizza l'utente alla pagina di gioco, passando eventualmente l'ID della partita o della storia come parametro
        this.router.navigateByUrl('/playPage');
      },
      error: (error) => {
        console.error('Errore nel riprendere la partita', error);
      }
    });
  }

  removeMatch(match: match) {
    this.matchService.deleteMatch(match.id);
    location.reload(); //per fare il refresh dell'applicazione
  }
}