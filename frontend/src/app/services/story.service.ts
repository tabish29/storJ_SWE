import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { story } from '../story';
import { LocalStorageService } from './local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class StoryService {
  private apiServerUrl='http://localhost:8080/api/v1';
  private storySource = new BehaviorSubject<story | null>(this.loadInitialStory());
  currentStory = this.storySource.asObservable();
 

  constructor(private http: HttpClient,private localStorageService: LocalStorageService ) { }

  public getAllstories(): Observable<story[]>{
    //mettere l'url del metodo get di tutte le storie
    return this.http.get<story[]>(this.apiServerUrl + '/storie');
  }

  public addstory(story: story): Observable<story>{
    //mettere l'url del metodo post 
    return this.http.post<story>(this.apiServerUrl + '/storie', story); 
  }

  public filterStories(filterType: string, filterValue: string): Observable<story[]> {
    let queryParams = '';
  
    switch(filterType) {
      case 'Autore':
        queryParams += `autore=${encodeURIComponent(filterValue)}`;
        break;
      case 'Categoria':
        queryParams += `categoria=${encodeURIComponent(filterValue)}`;
        break;
      case 'Numero Scenari':
        queryParams += `numScenari=${encodeURIComponent(filterValue)}`;
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
  
}
