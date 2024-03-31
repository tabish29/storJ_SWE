import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { ScenarioService } from './scenario.service';
import { UserService } from './userservice';
import { match } from '../match';

@Injectable({
  providedIn: 'root'
})
export class MatchService {


  private apiServerUrl = 'http://localhost:8080/api/v1';
  private matchSource = new BehaviorSubject<match | null>(this.loadInitialMatch());
  currentMatch = this.matchSource.asObservable();
  matchMap: Map<number, string | undefined> = new Map();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService, private scenarioService: ScenarioService, private userService: UserService) { }

  public getMatchByUser(idUser: number): Observable<match[]> {

    return this.http.get<match[]>(this.apiServerUrl + '/utenti/' + idUser + '/partite');
  }

  public getMatch(idmatch: number): Observable<match> {

    return this.http.get<match>(this.apiServerUrl + '/partite/' + idmatch);
  }

  public addMatch(match: match): Observable<match> {

    return this.http.post<match>(this.apiServerUrl + '/partite', match);
  }

  deleteMatch(matchId: number): void {
    this.http.delete(this.apiServerUrl + '/partite/' + matchId).subscribe({
      next: () => {
        alert('partita eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione della partita', error.message);
      }
    });

  }

  loadInitialMatch(): match | null {
    return this.localStorageService.getItem('currentMatch');
  }

  changeMatch(newmatch: match) {
    this.matchSource.next(newmatch);
    this.localStorageService.setItem('currentMatch', newmatch);

  }

  getCurrentMatch(): match | null {
    return this.matchSource.value;
  }
}
