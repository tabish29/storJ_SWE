import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { scenario } from '../scenario';

@Injectable({
  providedIn: 'root'
})
export class ScenarioService {

  private apiServerUrl='http://localhost:8080/api/v1';
  private scenarioSource = new BehaviorSubject<scenario | null>(this.loadInitialscenario());
  currentscenario = this.scenarioSource.asObservable();
 

  constructor(private http: HttpClient,private localStorageService: LocalStorageService ) { }

  public getAllstories(): Observable<scenario[]>{
    //mettere l'url del metodo get di tutte le storie
    return this.http.get<scenario[]>(this.apiServerUrl + '/storie');
  }

  public addscenario(scenario: scenario): Observable<scenario>{
    //mettere l'url del metodo post 
    return this.http.post<scenario>(this.apiServerUrl + '/storie', scenario); 
  }

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

   loadInitialscenario(): scenario | null {
    return this.localStorageService.getItem('currentscenario');
  }
  
  changescenario(newscenario: scenario) {
    this.scenarioSource.next(newscenario);
    this.localStorageService.setItem('currentscenario', newscenario);
   
  }

  getCurrentscenario(): scenario | null {
    return this.scenarioSource.value;
  }

}
