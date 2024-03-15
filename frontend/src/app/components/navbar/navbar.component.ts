import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/userservice';
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


  constructor(private router: Router, private authService: AuthService,private userService: UserService) {}

  ngOnDestroy(): void {
      this.userSub.unsubscribe(); // elimina la sottoscrizione
    
  }

  ngOnInit() {
    this.userSub=this.userService.currentUser.subscribe((user: user | null) => {
      if (user) {
        this.showPaymentButton = !user.statoPagamento;
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
