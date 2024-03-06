import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router: Router) {
  }

  

  logout() {
    // Rimuovi il token di accesso memorizzato in localStorage
    localStorage.removeItem('angular17token');
    // Reindirizza l'utente alla pagina di login o ad altre pagine desiderate dopo il logout
    this.router.navigateByUrl('/login');
  }

}
