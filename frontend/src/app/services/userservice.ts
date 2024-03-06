import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { user } from '../user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl='http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }

  public getUserByUsername(username: string): Observable<user>{
    return this.http.get<user>(this.apiServerUrl + '/utenti/' + username);
  }

  public addUser(user: user): Observable<user>{
    return this.http.post<user>(this.apiServerUrl + '/utenti', user);
  }
}
