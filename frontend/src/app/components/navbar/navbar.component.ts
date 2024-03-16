import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, OnDestroy{
  showPaymentButton = false;
  private userSub!: Subscription;
  currentUrl!: string;
  private subscription: Subscription = new Subscription();


  constructor(private router: Router, private authService: AuthService,private userService: UserService) {
     // Imposta l'URL corrente al valore iniziale
     this.currentUrl = router.url;
  }

  ngOnDestroy(): void {
      this.userSub.unsubscribe(); // elimina la sottoscrizione
    
  }

  ngOnInit() {
     // Ascolta gli eventi di navigazione per aggiornare l'URL corrente
     this.subscription.add(
      this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe(event => {
        if (event instanceof NavigationEnd) { 
          this.currentUrl = event.url;
        }
      })
    );
    
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
    console.log('valore del current url'+this.currentUrl)
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
