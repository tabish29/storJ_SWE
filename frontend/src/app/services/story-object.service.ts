import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { storyObject } from '../storyObject';

@Injectable({
  providedIn: 'root'
}) export class storyObjectService {

  private apiServerUrl = 'http://localhost:8080/api/v1';
  private storyObjectSource = new BehaviorSubject<storyObject | null>(this.loadInitialStoryObject());
  currentstoryObject = this.storyObjectSource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getStoryObjectByStoryId(idStoria: number): Observable<storyObject[]> {

    return this.http.get<storyObject[]>(this.apiServerUrl + '/storie/' + idStoria + '/oggetti');
  }

  public getStoryObject(idStoryObject: number): Observable<storyObject> {

    return this.http.get<storyObject>(this.apiServerUrl + '/oggetti/' + idStoryObject);
  }

  public addStoryObject(storyObject: storyObject): Observable<storyObject> {

    return this.http.post<storyObject>(this.apiServerUrl + '/oggetti', storyObject);
  }

  deleteStoryObject(storyObjectId: number): void {
    this.http.delete(this.apiServerUrl + '/oggetti/' + storyObjectId).subscribe({
      next: () => {
        alert('storyObject eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello storyObject', error.message);
      }
    });

  }

  loadInitialStoryObject(): storyObject | null {
    return this.localStorageService.getItem('currentstoryObject');
  }

  changestoryObject(newstoryObject: storyObject) {
    this.storyObjectSource.next(newstoryObject);
    this.localStorageService.setItem('currentstoryObject', newstoryObject);

  }

  getCurrentstoryObject(): storyObject | null {
    return this.storyObjectSource.value;
  }

}
