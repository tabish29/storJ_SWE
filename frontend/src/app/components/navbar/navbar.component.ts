import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { DataService } from '../../services/data.service';
import { user } from '../../user';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, OnDestroy{
  showPaymentButton = false;
  private userSub!: Subscription;


  constructor(private router: Router, private authService: AuthService,private dataService: DataService) {}

  ngOnDestroy(): void {
      this.userSub.unsubscribe(); // elimina la sottoscrizione
    
  }

  ngOnInit() {
    this.userSub=this.dataService.currentUser.subscribe((user: user | null) => {
      if (user) {
        this.showPaymentButton = !user.statoPagamento;
        console.log("valore di paid dell'user che ci viene passato:" + user.statoPagamento);
        console.log("Nome dell'utente che effettuato il login:" + user.username);
        console.log("Id dell'utente che effettuato il login:" + user.id);
      } else {
        // Se user è null, imposta showPaymentButton a false
        this.showPaymentButton = false;
        
      }

    },error=>{
      console.log('error:',error);
    });
  }

  // Metodo per verificare se l'utente è autenticato
  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  // Metodo per effettuare il logout
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Reindirizza l'utente alla pagina di login dopo il logout
  }

}
