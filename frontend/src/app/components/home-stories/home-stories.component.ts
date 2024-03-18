import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { story } from '../../story';
import { StoryService } from '../../services/story.service';

@Component({
  selector: 'app-home-stories',
  templateUrl: './home-stories.component.html',
  styleUrl: './home-stories.component.css'
})
export class HomeStoriesComponent {
  //Da modificare fare in modo che vengano visualizzate solo le storie dell'utente loggato(da fare)
  stories: story[] = [];
  filterType='';
  filterValue='';
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

  //da modificare mettere la modifica del testo
  playStory(story: story): void {
    this.storyService.changeStory(story);
    this.router.navigateByUrl(`storJPage`);
  }

  filterStories(filterType: string, filterValue: string): void {
    this.storyService.filterStories(filterType, filterValue)
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
