import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { storyObject } from '../../storyObject';
import { storyObjectService } from '../../services/story-object.service';


@Component({
  selector: 'app-story-objects',
  templateUrl: './story-objects.component.html',
  styleUrl: './story-objects.component.css'
})
export class StoryObjectsComponent {
 
//Da modificare fare in modo che vengano visualizzate solo le storie dell'utente loggato
storyObjects: storyObject[] = [];
storyId!: number;

constructor(private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService) { }

ngOnInit(): void {
  // Recupera l'ID della storia corrente dal localStorage
  const currentStory = this.localStorageService.getItem('currentStory');
  this.storyId = currentStory.id;
  this.loadstoryObjects();
}

loadstoryObjects(): void {
  this.storyObjectService.getstoryObjectByStoryId(this.storyId).subscribe(
    (storyObjects: storyObject[]) => {
      this.storyObjects = storyObjects;
    },
    error => {
      console.error('Errore nel caricamento delle storie', error);
    }
  );
}

removestoryObject(storyObjectId: number): void {
  this.storyObjectService.deletestoryObject(storyObjectId);
  this.loadstoryObjects();
  location.reload(); //per fare il refresh dell'applicazione
}

}
