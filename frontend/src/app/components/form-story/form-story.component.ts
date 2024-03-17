import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { StoryService } from '../../services/story.service';
import { story } from '../../story';
import { LocalStorageService } from '../../services/local-storage.service';

@Component({
  selector: 'app-form-story',
  templateUrl: './form-story.component.html',
  styleUrl: './form-story.component.css'
})
export class FormStoryComponent implements OnInit{
    //inserire le variabili che servono nel form della creazione della storia 

    idCreatore = -1; //valore predefinito(gestire il caso in cui non si recuperi il dato da localstorage)
    titolo = ''; //dal form
    categoria = ''; //dal form
  
    constructor(private http: HttpClient, private storyService: StoryService, private router: Router,private localStorageService:LocalStorageService) { }

    ngOnInit() {
      this.loadCreatorId();
    }

    loadCreatorId() {
      const currentUser = this.localStorageService.getItem('currentUser');
      if (currentUser) {
        this.idCreatore = currentUser.id;
      } else {
        alert('ID creatore non trovato');
      }
    }
  
    public savestory(story: story): void {
      this.storyService.addstory(story).subscribe(
        (response: story) => {
          this.storyService.changeStory(response);
          alert("Storia creata con successo!")
     
          this.router.navigateByUrl('/createStory');
  
        },
        (error: HttpErrorResponse) => {
          //gestire i vari codici di errore che arrivano da parte della richiesta http(da fare)
            alert(error.error.message);
          
        }
      );
    }
  
    onSubmit() {
  
      const storyData: story = {
        id: 0,
        id_creatore: this.idCreatore, //dal localstorage
        titolo: this.titolo, //dal form
        categoria: this.categoria, //dal form
        numero_scenari: 0,
        statoCompletamento: false
      };
  
      this.savestory(storyData);
    }
  

}
