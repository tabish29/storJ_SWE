import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedIn = false;

  constructor() {}

  // Metodo per impostare lo stato di autenticazione e setto un token da memorizzare nel localStorage per la persistenza tra le pagines
  setAuthStatus(isAuthenticated: boolean, token: string = 'tokenTest'): void {
    if (isAuthenticated) {
      localStorage.setItem('authToken',token);
      this.isLoggedIn = isAuthenticated;
    } else {
      localStorage.removeItem('authToken');
      this.isLoggedIn = isAuthenticated;
    }
  }

  isAuthenticated(): boolean {
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('authToken');
      return !!token;
    }
    // Ritorna un valore predefinito o gestisci il caso in cui il codice è eseguito lato server
    return false;
  }

  logout(): void {
    this.isLoggedIn = false;
    localStorage.removeItem('authToken');
    localStorage.removeItem('currentUser');
  }
}
