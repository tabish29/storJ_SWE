import { Component, PipeTransform } from '@angular/core';
import { story } from '../../story';
import { StoryService } from '../../services/story.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { Observable, map } from 'rxjs';
import { user } from '../../user';

@Component({
  selector: 'app-handler-playpage',
  templateUrl: './handler-playpage.component.html',
  styleUrl: './handler-playpage.component.css'
})

//QUANDO CLICCI GIOCA CHIAMATA API PER PRENDERE TUTTE LE STORIE, DA CUI SI PRENDERANNO NOME, AUTORE, NUMERO SCENARI E BOTTONE --> GIOCA

export class HandlerPlaypageComponent {
  stories: story[] = [];
  filterType = '';
  filterValue = '';
  noStoriesFound: boolean = false; //nel caso in cui l'array delle storie dopo il filtraggio fosse vuoto
  authorMap: Map<number, String | undefined> = new Map();

  constructor(private storyService: StoryService, private router: Router, private localStorageService: LocalStorageService, private userService: UserService) { }

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

  playStory(story: story): void {
    this.storyService.changeStory(story);

    // Reindirizzamento all'URL di gioco della storia
    this.router.navigateByUrl(`playPage`);
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