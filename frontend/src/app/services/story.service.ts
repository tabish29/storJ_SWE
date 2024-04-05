import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, throwError } from 'rxjs';
import { story } from '../story';
import { LocalStorageService } from './local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class StoryService {
  private apiServerUrl = 'http://localhost:8080/api/v1';
  private storySource = new BehaviorSubject<story | null>(this.loadInitialStory());
  currentStory = this.storySource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }


  public getStoriesByUsername(idUser: number): Observable<story[]> {
    return this.http.get<story[]>(this.apiServerUrl + '/utenti/' + idUser + '/storie');
  }

  public getStoriesByStoryID(idStory: number): Observable<story> {
    return this.http.get<story>(this.apiServerUrl + '/storie/' + idStory);
  }

  public getAllStories(): Observable<story[]> {
    //mettere l'url del metodo get di tutte le storie
    return this.http.get<story[]>(this.apiServerUrl + '/storie');
  }

  public addStory(story: story): Observable<story> {
    //mettere l'url del metodo post 
    return this.http.post<story>(this.apiServerUrl + '/storie', story);
  }

  deleteStory(storyId: number): void {
    this.http.delete(this.apiServerUrl + '/storie/' + storyId).subscribe({
      next: () => {
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione della storia', error.message);
      }
    });

  }

  public saveStory(): Observable<story> {
    const currentStory: story = this.localStorageService.getItem('currentStory');
    const url = this.apiServerUrl + '/storie/' + currentStory.id + '/save';

    return this.http.post<story>(url, null);
  }

  public filterStories(filterType: string, filterValue: string): Observable<story[]> {
    let queryParams = '';

    switch (filterType) {
      case 'Autore':
        queryParams += `autore=${encodeURIComponent(filterValue)}`;
        break;
      case 'Categoria':
        queryParams += `categoria=${encodeURIComponent(filterValue)}`;
        break;
      case 'Numero Scenari':
        queryParams += `numScenari=${encodeURIComponent(filterValue)}`;
        break;
      case '-1':
        this.getAllStories();
        break;
      default:
        console.error('Tipo di filtro non supportato');
    }

    return this.http.get<story[]>(`${this.apiServerUrl}/storie?${queryParams}`);
  }

  loadInitialStory(): story | null {
    return this.localStorageService.getItem('currentStory');
  }

  changeStory(newStory: story) {
    this.storySource.next(newStory);
    this.localStorageService.setItem('currentStory', newStory);

  }

  getCurrentStory(): story | null {
    return this.storySource.value;
  }

  public isStoryCompleted(): boolean {
    // Recupero la storia corrente dal localStorage
    const currentStory: story = this.localStorageService.getItem('currentStory');

    if (currentStory && currentStory.statoCompletamento) {
      return true;
    } else {
      return false;
    }
  }

}