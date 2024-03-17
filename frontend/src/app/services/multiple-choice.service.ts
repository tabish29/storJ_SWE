import { Injectable } from '@angular/core';
import { multipleChoice } from '../multipleChoice';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MultipleChoiceService {


  private apiServerUrl = 'http://localhost:8080/api/v1';
  private multipleChoiceSource = new BehaviorSubject<multipleChoice | null>(this.loadInitialmultipleChoice());
  currentmultipleChoice = this.multipleChoiceSource.asObservable();


  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getmultipleChoiceByScenarioId(idScenario: number): Observable<multipleChoice[]> {
   
    return this.http.get<multipleChoice[]>(this.apiServerUrl + '/scenari/' + idScenario + '/scelte/multipla');
  }

  public addmultipleChoice(multipleChoice: multipleChoice): Observable<multipleChoice> {
    
    return this.http.post<multipleChoice>(this.apiServerUrl + '/scelte/multipla', multipleChoice);
  }

  deletemultipleChoice(multipleChoiceId: number): void {
    this.http.delete(this.apiServerUrl + '/scelte/' + multipleChoiceId+'/multipla').subscribe({ 
      next: () => {
        alert('multipleChoice eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello multipleChoice', error.message);
      }
    });

  }


  loadInitialmultipleChoice(): multipleChoice | null {
    return this.localStorageService.getItem('currentMultipleChoice');
  }

  changemultipleChoice(newmultipleChoice: multipleChoice) {
    this.multipleChoiceSource.next(newmultipleChoice);
    this.localStorageService.setItem('currentMultipleChoice', newmultipleChoice);

  }

  getCurrentmultipleChoice(): multipleChoice | null {
    return this.multipleChoiceSource.value;
  }

}
