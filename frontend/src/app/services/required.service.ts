import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { required } from '../required';
import { LocalStorageService } from './local-storage.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequiredService {
  private apiServerUrl = 'http://localhost:8080/api/v1';
  private requiredSource = new BehaviorSubject<required | null>(this.loadInitialRequired());
  currentRequired = this.requiredSource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getRequiredByScenarioId(idChoiche: number): Observable<required> {
    return this.http.get<required>(this.apiServerUrl + '/scelte/' + idChoiche + '/required');
  }

  public getRequired(idRequired: number): Observable<required> {
    return this.http.get<required>(this.apiServerUrl + '/required/' + idRequired);
  }

  public addRequired(required: required): Observable<required> {
    return this.http.post<required>(this.apiServerUrl + '/required', required);
  }

  deleteRequired(idRequired: number): void {
    this.http.delete(this.apiServerUrl + '/required/' + idRequired).subscribe({
      next: () => {
        //alert('required eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Errore durante eliminazione dello required', error.message);
      }
    });
  }

  loadInitialRequired(): required | null {
    return this.localStorageService.getItem('currentRequired');
  }

  changeRequired(newRequired: required) {
    this.requiredSource.next(newRequired);
    this.localStorageService.setItem('currentRequired', newRequired);
  }

  getCurrentRequired(): required | null {
    return this.requiredSource.value;
  }

}