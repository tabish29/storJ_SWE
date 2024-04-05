import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../services/local-storage.service';
import { storyObject } from '../../storyObject';
import { storyObjectService } from '../../services/story-object.service';
import { StoryService } from '../../services/story.service';


@Component({
  selector: 'app-story-objects',
  templateUrl: './story-objects.component.html',
  styleUrl: './story-objects.component.css'
})
export class StoryObjectsComponent {

  //Da modificare fare in modo che vengano visualizzate solo le storie dell'utente loggato
  storyObjects: storyObject[] = [];
  storyId!: number;
  isInTextEditMode!: boolean;

  constructor(private storyObjectService: storyObjectService, private router: Router, private localStorageService: LocalStorageService, private storyService: StoryService) { }

  ngOnInit(): void {
    // Recupera l'ID della storia corrente dal localStorage
    const currentStory = this.localStorageService.getItem('currentStory');
    this.storyId = currentStory.id;
    this.loadstoryObjects();
    this.isInTextEditMode = this.storyService.isStoryCompleted();
    
  }

  loadstoryObjects(): void {
    this.storyObjectService.getStoryObjectByStoryId(this.storyId).subscribe(
      (storyObjects: storyObject[]) => {
        // Ordino gli oggetti in base all'Id
        this.storyObjects = storyObjects.sort((a, b) => a.id - b.id);
      },
      error => {
        console.error('Errore nel caricamento delle storie', error);
      }
    );
  }

  removestoryObject(storyObjectId: number): void {
    this.storyObjectService.deleteStoryObject(storyObjectId);
    this.loadstoryObjects();
    location.reload(); //per fare il refresh dell'applicazione
  }

  editStoryObjectText(newStoryObject: storyObject) {
    this.storyObjectService.changeStoryObject(newStoryObject);
    this.router.navigateByUrl("/formStoryObject");
  }
}