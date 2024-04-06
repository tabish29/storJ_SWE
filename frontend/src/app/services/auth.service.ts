import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedIn = false;

  constructor(private localStorageService: LocalStorageService) { }

  setAuthStatus(isAuthenticated: boolean, token: string = 'tokenTest'): void {
    if (isAuthenticated) {
      localStorage.setItem('authToken', token);
      this.isLoggedIn = isAuthenticated;
    } else {
      localStorage.removeItem('authToken');
      this.isLoggedIn = isAuthenticated;
    }
  }

  isAuthenticated(): boolean {
    if (typeof window !== 'undefined') {
      const token = localStorage.getItem('authToken');
      this.isLoggedIn = !!token;
      return this.isLoggedIn;
    }
    return this.isLoggedIn;
  }

  logout(): void {
    this.isLoggedIn = false;
    this.localStorageService.clear();
  }

}