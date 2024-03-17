import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { storyObject } from '../storyObject';

@Injectable({
  providedIn: 'root'
}) export class storyObjectService {

  private apiServerUrl = 'http://localhost:8080/api/v1';
  private storyObjectSource = new BehaviorSubject<storyObject | null>(this.loadInitialstoryObject());
  currentstoryObject = this.storyObjectSource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getstoryObjectByStoryId(idStoria: number): Observable<storyObject[]> {
    //mettere l'url del metodo get da una storia specifica(prendere l'id della storia)
    return this.http.get<storyObject[]>(this.apiServerUrl + '/storie/' + idStoria + '/oggetti');
  }

  public addstoryObject(storyObject: storyObject): Observable<storyObject> {
    //mettere l'url del metodo post 
    return this.http.post<storyObject>(this.apiServerUrl + '/oggetti', storyObject);
  }

  deletestoryObject(storyObjectId: number): void {
    this.http.delete(this.apiServerUrl + '/oggetti/' + storyObjectId).subscribe({
      next: () => {
        alert('storyObject eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello storyObject', error.message);
      }
    });

  }

  loadInitialstoryObject(): storyObject | null {
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
