import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { drop } from '../drop';


@Injectable({
  providedIn: 'root'
})
export class DropService {

  private apiServerUrl = 'http://localhost:8080/api/v1';
  private dropSource = new BehaviorSubject<drop | null>(this.loadInitialDrop());
  currentdrop = this.dropSource.asObservable();


  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getDropByScenarioId(idScenario: number): Observable<drop> {

    return this.http.get<drop>(this.apiServerUrl + '/scenari/' + idScenario + '/drop');
  }

  public getDrop(idDrop: number): Observable<drop> {

    return this.http.get<drop>(this.apiServerUrl + '/drop/' + idDrop);
  }

  public addDrop(drop: drop): Observable<drop> {

    return this.http.post<drop>(this.apiServerUrl + '/drop', drop);
  }

  deleteDrop(dropId: number): void {
    this.http.delete(this.apiServerUrl + '/drop/' + dropId).subscribe({
      next: () => {
        alert('drop eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello drop', error.message);
      }
    });

  }

  loadInitialDrop(): drop | null {
    return this.localStorageService.getItem('currentDrop');
  }

  changeDrop(newDrop: drop) {
    this.dropSource.next(newDrop);
    this.localStorageService.setItem('currentDrop', newDrop);

  }

  getCurrentDrop(): drop | null {
    return this.dropSource.value;
  }

}
