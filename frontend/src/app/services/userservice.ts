import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { user } from '../user';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = 'http://localhost:8080/api/v1';
  private userSource = new BehaviorSubject<user | null>(this.loadInitialUser());
  currentUser = this.userSource.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  public getUserByUsername(username: string): Observable<user> {
    return this.http.get<user>(this.apiServerUrl + '/utenti/username/' + username);
  }

  public getUserById(idUtente: number): Observable<user> {
    return this.http.get<user>(this.apiServerUrl + '/utenti/' + idUtente);
  }

  public addUser(user: user): Observable<user> {
    return this.http.post<user>(this.apiServerUrl + '/utenti', user);
  }

  private loadInitialUser(): user | null {
    return this.localStorageService.getItem('currentUser');
  }

  changeUser(newUser: user) {
    this.userSource.next(newUser);
    this.localStorageService.setItem('currentUser', newUser);
  }

  getCurrentUser(): user | null {
    return this.userSource.value;
  }

  getCurrentUserName(): String {
    const currentUser = this.getCurrentUser();
    return currentUser ? currentUser.username : ' ';
  }

  updateUserPaymentStatus(isPaid: boolean): void {
    const currentUser = this.getCurrentUser();
    if (currentUser) {
      const updatedUser = { ...currentUser, statoPagamento: isPaid };
      this.changeUser(updatedUser);
    }
  }

}