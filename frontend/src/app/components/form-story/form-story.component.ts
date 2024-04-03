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
export class FormStoryComponent implements OnInit {
  idCreatore = -1;
  titolo = ' ';
  categoria = ' ';

  constructor(private http: HttpClient, private storyService: StoryService, private router: Router, private localStorageService: LocalStorageService) { }

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

    if (this.titolo == ' ' || this.categoria == ' ') {
      alert("Inserisci tutti i campi obbligatori (*)");
    }else{
      this.savestory(storyData);
    }
  }

}

