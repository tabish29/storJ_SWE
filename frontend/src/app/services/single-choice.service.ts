import { Injectable } from '@angular/core';
import { singleChoice } from '../singleChoice';
import { LocalStorageService } from './local-storage.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SingleChoiceService {
  private apiServerUrl = 'http://localhost:8080/api/v1';
  private singleChoiceSource = new BehaviorSubject<singleChoice | null>(this.loadInitialSingleChoice());
  private isChoiceCreated!: boolean;
  currentsingleChoice = this.singleChoiceSource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getSingleChoiceByScenarioId(idScenario: number): Observable<singleChoice> {
    return this.http.get<singleChoice>(this.apiServerUrl + '/scenari/' + idScenario + '/scelte/indovinello');
  }

  public addSingleChoice(singleChoice: singleChoice): Observable<singleChoice> {
    return this.http.post<singleChoice>(this.apiServerUrl + '/scelte/indovinello', singleChoice);
  }

  public updateSingleChoice(singleChoice: singleChoice): Observable<singleChoice> {
    return this.http.put<singleChoice>(this.apiServerUrl + '/scelte/' + singleChoice.id + '/indovinello', singleChoice);
  }

  deleteSingleChoice(singleChoiceId: number): void {
    this.http.delete(this.apiServerUrl + '/scelte/' + singleChoiceId + '/indovinello').subscribe({
      next: () => {
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello singleChoice', error.message);
      }
    });

  }

  loadInitialSingleChoice(): singleChoice | null {
    return this.localStorageService.getItem('currentSingleChoice');
  }

  changeSingleChoice(newSingleChoice: singleChoice) {
    this.singleChoiceSource.next(newSingleChoice);
    this.localStorageService.setItem('currentSingleChoice', newSingleChoice);
  }

  getCurrentsingleChoice(): singleChoice | null {
    return this.singleChoiceSource.value;
  }

  setIsChoiceCreated(isChoiceCreated: boolean): void {
    this.isChoiceCreated = isChoiceCreated;
  }

  getIsChoiceCreated() {
    return this.isChoiceCreated;
  }

}