import { Component } from '@angular/core';
import { story } from '../../story';
import { StoryService } from '../../services/story.service';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-handler-playpage',
  templateUrl: './handler-playpage.component.html',
  styleUrl: './handler-playpage.component.css'
})

//QUANDO CLICCI GIOCA CHIAMATA API PER PRENDERE TUTTE LE STORIE, DA CUI SI PRENDERANNO NOME, AUTORE, NUMERO SCENARI E BOTTONE --> GIOCA

export class HandlerPlaypageComponent {
  stories: story[] = [];
  filterAuthor: string | undefined;
  filterCategory: string | undefined;
  noStoriesFound: boolean = false; //nel caso in cui l'array delle storie dopo il filtraggio fosse vuoto

  constructor(private storyService: StoryService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.loadStories();
  }

  loadStories(): void {
    this.storyService.getAllstories().subscribe(
      (stories: story[]) => {
        this.stories = stories;
      },
      error => {
        console.error('Errore nel caricamento delle storie', error);
      }
    );
  }

  playStory(story: story): void {
    this.storyService.changeStory(story);

    // Reindirizzamento all'URL di gioco della storia
    this.router.navigateByUrl(`storJPage`);
  }

  filterStories(filterAuthor?: string, filterCategory?: string): void {
    this.storyService.filterStories(filterAuthor, filterCategory)
      .subscribe(
        stories => {
          // Successo: aggiorna le storie visualizzate
          this.stories = stories;
          if(stories.length === 0){
            this.noStoriesFound = true;
          }else{
            this.noStoriesFound = false;
          }
        },
        (error: HttpErrorResponse) => {
            // Inserire i vari codici di errore dell'api di Davide(da fare)
            switch(error.error.code) {
              case "UtenteNotFound":
                alert(error.error.message);
                break;
              default:
                alert("Errore durante il filtraggio delle storie.");
            }

          //per vedere i codici di errore(da eliminare)
          console.error('Errore HTTP:', error.message);
        }
      );
    }
}