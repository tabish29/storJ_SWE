import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { scenario } from '../scenario';

@Injectable({
  providedIn: 'root'
})
export class ScenarioService {

  private apiServerUrl = 'http://localhost:8080/api/v1';
  private scenarioSource = new BehaviorSubject<scenario | null>(this.loadInitialScenario());
  currentscenario = this.scenarioSource.asObservable();


  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getScenarioByStoryId(idStoria: number): Observable<scenario[]> {
    //mettere l'url del metodo get da una storia specifica(prendere l'id della storia)
    return this.http.get<scenario[]>(this.apiServerUrl + '/storie/' + idStoria + '/scenari');
  }

  public addScenario(scenario: scenario): Observable<scenario> {
    //mettere l'url del metodo post 
    return this.http.post<scenario>(this.apiServerUrl + '/scenari', scenario);
  }

  deleteScenario(scenarioId: number): void {
    this.http.delete(this.apiServerUrl + '/scenari/' + scenarioId).subscribe({
      next: () => {
        alert('Scenario eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello scenario', error.message);
      }
    });

  }

  //cambiare la logica a seconda dei filtri che vogliamo usare
  public filterScenarios(filterAuthor?: string, filterCategory?: string): Observable<scenario[]> {
    let queryParams = '';
    if (filterAuthor) {
      queryParams += `autore=${encodeURIComponent(filterAuthor)}&`;
    }
    if (filterCategory) {
      queryParams += `categoria=${encodeURIComponent(filterCategory)}`;
    }

    return this.http.get<scenario[]>(`${this.apiServerUrl}/storie?${queryParams}`);
  }

  loadInitialScenario(): scenario | null {
    return this.localStorageService.getItem('currentScenario');
  }

  changeScenario(newscenario: scenario) {
    this.scenarioSource.next(newscenario);
    this.localStorageService.setItem('currentScenario', newscenario);

  }

  getCurrentScenario(): scenario | null {
    return this.scenarioSource.value;
  }

}
