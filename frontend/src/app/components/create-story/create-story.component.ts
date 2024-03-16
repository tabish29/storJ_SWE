import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { StoryService } from '../../services/story.service';
import { story } from '../../story';


@Component({
  selector: 'app-create-story',
  templateUrl: './create-story.component.html',
  styleUrl: './create-story.component.css'
})
export class CreateStoryComponent{
  //inserire le variabili che servono nel form della creazione della storia 

  idCreatore = 3; //valore predefinito(gestire il caso in cui non si recuperi il dato da localstorage)
  titolo = ''; //dal form
  categoria = ''; //dal form

  constructor(private http: HttpClient, private storyService: StoryService, private router: Router) { }


  public savestory(story: story): void {
    this.storyService.addstory(story).subscribe(
      (response: story) => {
        alert("Storia creata con successo!")
        //redirect 
        this.router.navigateByUrl('/storJPage');

      },
      (error: HttpErrorResponse) => {
        //gestire i vari codici di errore che arrivano da parte della richiesta http
        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert(error.error.message);
        }
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
