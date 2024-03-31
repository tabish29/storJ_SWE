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
  stories: story[] = [];
  noStoriesFound: boolean = false;

  constructor(private storyService: StoryService, private router: Router, private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.loadStories();
  }

  loadStories(): void {
    const currentUser = this.localStorageService.getItem('currentUser');
    this.storyService.getStoriesByUsername(currentUser.id).subscribe(
      (stories: story[]) => {
        this.stories = stories;
      },
      error => {
        console.error('Errore nel caricamento delle storie', error);
      }
    );
  }

  editStory(newStory: story) {
    this.storyService.changeStory(newStory);
    this.router.navigateByUrl('createStory');
  }
}