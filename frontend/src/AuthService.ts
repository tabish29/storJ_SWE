import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = false;

  constructor(private http: HttpClient) { }

  // Metodo per verificare se l'utente è loggato
  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  // Metodo per effettuare il login
  login(username: string, password: string): void {
    // Esegui la richiesta HTTP GET all'API per verificare le credenziali
    //mettere l'url dell'api di davide
    this.http.get<any>('url-dell-api/login?username=' + username + '&password=' + password).subscribe(response => {
      if (response.result) {
        // Se le credenziali sono corrette, imposta il flag di accesso a true
        this.loggedIn = true;
      } else {
        // Altrimenti, gestisci l'errore o mostra un messaggio di errore
        console.error('Credenziali non valide');
      }
    });
  }

  // Metodo per effettuare il logout
  logout(): void {
    // Resetta il flag di accesso
    this.loggedIn = false;
  }
}
