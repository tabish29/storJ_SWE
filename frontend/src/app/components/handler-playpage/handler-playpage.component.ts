import { Component, PipeTransform } from '@angular/core';
import { story } from '../../story';
import { StoryService } from '../../services/story.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { Observable, map } from 'rxjs';
import { user } from '../../user';
import { MatchService } from '../../services/match.service';
import { match } from '../../match';
import { scenario } from '../../scenario';
import { ScenarioService } from '../../services/scenario.service';

@Component({
  selector: 'app-handler-playpage',
  templateUrl: './handler-playpage.component.html',
  styleUrl: './handler-playpage.component.css'
})

//QUANDO CLICCI GIOCA CHIAMATA API PER PRENDERE TUTTE LE STORIE, DA CUI SI PRENDERANNO NOME, AUTORE, NUMERO SCENARI E BOTTONE --> GIOCA

export class HandlerPlaypageComponent {
  stories: story[] = [];
  currentScenario!:scenario;
  filterType = '';
  filterValue = '';
  noStoriesFound: boolean = false; //nel caso in cui l'array delle storie dopo il filtraggio fosse vuoto
  authorMap: Map<number, String | undefined> = new Map();

  constructor(
    private storyService: StoryService,
    private router: Router,
    private localStorageService: LocalStorageService,
    private userService: UserService,
    private scenarioService:ScenarioService,
    private matchService: MatchService) { }

  async ngOnInit(): Promise<void> {
    await this.loadStories();
    await this.loadAuthorName(this.stories);
  }

  async loadStories(): Promise<void> {
    try {
      const stories = await (this.storyService.getAllstories()).toPromise();
      if (stories) {
        // Ordino gli scenari in base all'Id delle storie slavate
        this.stories = stories.sort((a, b) => a.id - b.id);
      }

    } catch (error) {
      console.log('Errore nel caricamento delle storie Salvate', error);
    }
  }

  async loadAuthorName(stories: story[]): Promise<void> {
    for (const story of stories) {
      try {
        const authorName = await this.userService.getUserById(story.id_creatore).toPromise();

        if (authorName) {
          this.authorMap.set(story.id, authorName.username);
        } else {
          this.authorMap.set(story.id, "Nessun Autore");
        }

      } catch (error) {
        console.log("Errore nel caricamento dei nome dell'autore per la storia " + story.id + ": " + error);
      }
    }
  }

  async loadInitialScenario(storyId: number): Promise<void> {
    try {
      const firstScenario = await this.scenarioService.getFirstScenario(storyId).toPromise();

      if(firstScenario){
        this.scenarioService.changeScenario(firstScenario[0]);
      }

    } catch (error) {
      console.error('Errore nel caricamento degli scenari', error);
    }
  }


  async  playStory(story: story): Promise<void> {
    this.storyService.changeStory(story);
    await this.loadInitialScenario(story.id)
    const currentUserId= this.userService.getCurrentUser()?.id;
    const firstScenarioId=this.scenarioService.getCurrentScenario()?.id
    if(currentUserId && firstScenarioId){
    //mettere i valori corretti
    const matchData: match = {
      id: 0,
      id_storia: story.id,
      id_utente: currentUserId,
      id_scenario_corrente: firstScenarioId
    };
    this.saveMatch(matchData);
  }

    // Reindirizzamento all'URL di gioco della storia
    this.router.navigateByUrl(`playPage`);
  }

  public saveMatch(match: match): void {

    this.matchService.addMatch(match).subscribe(
      (response: match) => {
        this.matchService.changeMatch(response);
        console.log("Partita creata con successo!");

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

  filterStories(filterType: string, filterValue: string): void {
    this.filterType = filterType;
    this.filterValue = filterValue;
    this.storyService.filterStories(this.filterType, this.filterValue).subscribe(
      stories => {
        //In caso di successo aggiorna le storie visualizzate
        this.stories = stories;
        if (stories.length === 0) {
          this.noStoriesFound = true;
        } else {
          this.noStoriesFound = false;
        }
      },
      (error: HttpErrorResponse) => {
        this.stories = [];
        this.noStoriesFound = true;
        // Inserire i vari codici di errore dell'api di Davide(da fare)
        switch (error.error.code) {
          case "UtenteNotFound":
            //alert(error.error.message);
            break;
          default:
            alert("Errore durante il filtraggio delle storie.");
        }

        //per vedere i codici di errore(da eliminare)
        console.error('Errore HTTP:', error.error.code);
      }
    );
  }


}