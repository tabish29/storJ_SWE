import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../AuthService';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router: Router, private authService: AuthService) {
  }

  
  isLoggedIn(): boolean {
    return this.authService.isLoggedIn(); // Assumi che il servizio di autenticazione abbia un metodo isLoggedIn()
  }

  logout() {
    // Rimuovi il token di accesso memorizzato in localStorage
    localStorage.removeItem('angular17token');
    // Reindirizza l'utente alla pagina di login o ad altre pagine desiderate dopo il logout
    this.router.navigateByUrl('/login');
  }

}
