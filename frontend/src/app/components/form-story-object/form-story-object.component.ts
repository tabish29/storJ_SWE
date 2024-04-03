import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { storyObject } from '../../storyObject';
import { storyObjectService } from '../../services/story-object.service';
import { StoryService } from '../../services/story.service';

@Component({
  selector: 'app-form-story-object',
  templateUrl: './form-story-object.component.html',
  styleUrl: './form-story-object.component.css'
})
export class FormStoryObjectComponent {
  //inserire le variabili che servono nel form della creazione dello storyObject 

  idStoria = -1;
  nome = ' ';
  descrizione = ' ';
  isInTextEditMode!: boolean;
  currentStoryObject!: storyObject | null;

  constructor(private http: HttpClient, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService) { }

  ngOnInit(): void {
    this.loadStoryId();
    this.isInTextEditMode = this.storyService.isStoryCompleted();
    this.loadCurrentStoryObject();

  }

  loadCurrentStoryObject(): void {
    this.currentStoryObject = this.localStorageService.getItem("currentstoryObject");

  }

  loadStoryId() {
    const currentStory = this.localStorageService.getItem('currentStory');
    if (currentStory) {
      this.idStoria = currentStory.id;
    } else {
      alert('id storia non trovato');
    }
  }


  public savestoryObject(storyObject: storyObject): void {

    this.storyObjectService.addStoryObject(storyObject).subscribe(
      (response: storyObject) => {
        this.storyObjectService.changestoryObject(response);
        alert("storyObject creato con successo!")

        this.router.navigateByUrl('/storyObjects');

      },
      (error: HttpErrorResponse) => {
        //gestire i vari codici di errore che arrivano da parte della richiesta http(da fare)
        if (error.error.code == "UtenteAlreadySigned") {
          alert(error.error.message);
        } else {
          alert("c'è stato un errore:" + error.error.message + " id della storia salvata" + this.idStoria);
        }
      }
    );
  }

  onSubmit() {

    //decidere se tenere solo le informazioni necessarie per il back end(da decidere)
    const storyObjectData: storyObject = {
      id: 0,
      id_storia: this.idStoria,
      nome: this.nome,
      descrizione: this.descrizione
    };

    if (this.nome == ' ' || this.descrizione == ' ') {
      alert("Inserisci tutti i campi obbligatori (*)");
    }else{
      this.savestoryObject(storyObjectData);
    }

    
  }

  onNameChange(newName: string): void {
    if (this.currentStoryObject) {
      this.currentStoryObject.nome = newName;
      this.descrizione = this.currentStoryObject.descrizione;
    }
    this.nome = newName;

  }

  onDescriptionChange(newDescription: string): void {
    if (this.currentStoryObject) {
      this.currentStoryObject.descrizione = newDescription;
      this.nome = this.currentStoryObject.nome;
    }
    this.descrizione = newDescription;
  }

  // metodo per obbligare l'utente a modificare almeno uno dei due campi
  isUpdated(): boolean {
    return !this.nome.trim() || !this.descrizione.trim();
  }

  updateStoryObjectText() {

    if (this.currentStoryObject) {
      const newStoryObjectData: storyObject = {
        id: this.currentStoryObject?.id,
        id_storia: this.idStoria,
        nome: this.nome,
        descrizione: this.descrizione
      };


      this.storyObjectService.updateStoryObject(newStoryObjectData).subscribe({
        next: (updatedStoryObject) => {

          console.log("StoryObject aggiornato con successo:", updatedStoryObject);

          this.storyObjectService.changestoryObject(newStoryObjectData);

          this.router.navigateByUrl('/storyObjects').then(() => {
            // Ricarica la pagina dopo la navigazione(da implementare anche nello scenario,nel drop e nel required)
            window.location.reload();
          });
        },
        error: (error) => {

          console.error("Errore durante l'aggiornamento dello StoryObject:", error);
        }
      });


    }

  }


}