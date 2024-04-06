import { Component, PipeTransform } from '@angular/core';
import { story } from '../../story';
import { StoryService } from '../../services/story.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { MatchService } from '../../services/match.service';
import { match } from '../../match';
import { scenario } from '../../scenario';
import { ScenarioService } from '../../services/scenario.service';
import { PopupComponent } from '../popup/popup.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-handler-playpage',
  templateUrl: './handler-playpage.component.html',
  styleUrl: './handler-playpage.component.css'
})
export class HandlerPlaypageComponent {
  stories: story[] = [];
  currentScenario!: scenario;
  filterType = "-1";
  filterValue = '';
  noStoriesFound: boolean = false;
  authorMap: Map<number, String | undefined> = new Map();

  constructor(private storyService: StoryService, private router: Router, private localStorageService: LocalStorageService, private userService: UserService, private scenarioService: ScenarioService, private matchService: MatchService, private dialog: MatDialog) { }

  async ngOnInit(): Promise<void> {
    await this.loadStories();
    await this.loadAuthorName(this.stories);

  }

  async loadStories(): Promise<void> {
    try {
      const stories = await (this.storyService.getAllStories()).toPromise();
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

      if (firstScenario) {
        this.scenarioService.changeScenario(firstScenario[0]);
      }
    } catch (error) {
      console.error('Errore nel caricamento degli scenari', error);
    }
  }

  async playStory(story: story): Promise<void> {
    this.storyService.changeStory(story);
    await this.loadInitialScenario(story.id)
    const currentUserId = this.userService.getCurrentUser()?.id;
    const firstScenarioId = this.scenarioService.getCurrentScenario()?.id
    this.matchService.setIsFirstMatch(true);

    if (currentUserId && firstScenarioId) {
      const matchData: match = {
        id: 0,
        id_storia: story.id,
        id_utente: currentUserId,
        id_scenario_corrente: firstScenarioId
      };
      this.localStorageService.setItem('currentMatch', matchData);
      await this.saveMatch(matchData);

      this.router.navigateByUrl(`playPage`);
    }
  }

  async saveMatch(match: match): Promise<void> {
    try {
      const response = await this.matchService.addMatch(match);
      this.matchService.changeMatch(response);
      console.log("Partita creata con successo!");
    } catch (error) {
      if (error instanceof HttpErrorResponse) {

        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert("c'è stato un errore:" + error.error.message);
        }
      } else {
        console.error("Errore durante la creazione della partita:", error);
      }
    }
  }

  private async searchStoriesByTitle(searchValue: string): Promise<void> {
    await this.loadStories().then(() => {
      this.filterByTitle(searchValue);
    });
  }

  private filterByTitle(searchValue: string): void {
    const filteredStories = this.stories.filter(story =>
      story.titolo.toLowerCase().includes(searchValue.toLowerCase())
    );

    this.stories = filteredStories;
    this.noStoriesFound = filteredStories.length === 0;
  }

  filterStories(filterType: string, filterValue: string): void {
    this.filterType = filterType;
    this.filterValue = filterValue;

    if (filterType === "Titolo") {
      // Cerca nelle storie caricate quelle che contengono nel titolo la stringa inserita dall'utente 
      this.searchStoriesByTitle(filterValue);
    } else {
      this.storyService.filterStories(this.filterType, this.filterValue).subscribe(
        stories => {
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
          switch (error.error.code) {
            case "UtenteNotFound":
              //alert(error.error.message);
              break;
            default:
              alert("Errore durante il filtraggio delle storie.");
          }
          console.error('Errore HTTP:', error.error.code);
        }
      );
    }

  }

  openPreview(story: story): void {
    this.dialog.open(PopupComponent, {
      data: {
        showPreview: true,
        story: story
      }
    });
  }

}