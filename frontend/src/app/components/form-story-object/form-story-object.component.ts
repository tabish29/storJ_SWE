import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { storyObject } from '../../storyObject';
import { storyObjectService } from '../../services/story-object.service';

@Component({
  selector: 'app-form-story-object',
  templateUrl: './form-story-object.component.html',
  styleUrl: './form-story-object.component.css'
})
export class FormStoryObjectComponent {
   //inserire le variabili che servono nel form della creazione dello storyObject 

   idStoria = -1;
   nome='';
   descrizione='';

   constructor(private http: HttpClient, private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService) { }
 
   ngOnInit(): void {
     this.loadStoryId();
 
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
 
     this.savestoryObject(storyObjectData);
   }
 

}
