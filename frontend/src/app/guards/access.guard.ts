import { CanActivateFn } from '@angular/router';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; 

@Injectable({
  providedIn: 'root'
})
export class accessGuard {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate: CanActivateFn = (route, state) => {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigateByUrl('/login'); // Reindirizza alla pagina di login se non autenticato
      return false;
    }
  }
}