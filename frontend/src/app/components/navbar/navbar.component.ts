import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Subscription, throwError } from 'rxjs';
import { catchError, filter } from 'rxjs/operators';
import { StoryService } from '../../services/story.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})

export class NavbarComponent implements OnInit, OnDestroy {
  showPaymentButton = false;
  private userSub!: Subscription;
  currentUrl!: string;
  private subscription: Subscription = new Subscription();


  constructor(private router: Router, private authService: AuthService, private userService: UserService, private storyService: StoryService) {
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

    this.userSub = this.userService.currentUser.subscribe((user: user | null) => {
      if (user) {
        this.showPaymentButton = !user.statoPagamento;
      } else {
        // Se user è null, imposta showPaymentButton a false
        this.showPaymentButton = false;
      }
    }, error => {
      console.log('error:', error);
    });
    console.log('valore del current url' + this.currentUrl)
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

  saveStory(): void {
    this.storyService.saveStory().subscribe(
      (response: any) => {
        alert('salvataggio avvenuto con successo')
      },
      (error: HttpErrorResponse) => {
        switch (error.error.code) {
          case "SalvataggioFailedScenarioIniziale":
            alert(error.error.message);
            break;
          case "SalvataggioFailedScenarioFinale":
            alert(error.error.message);
            break;
          case "SalvataggioFailedMultipla":
            alert(error.error.message);
            break;
          case "SalvataggioFailedIndovinello":
            alert(error.error.message);
            break;
          default:
            //(Da controllare)
            alert(error.error.message);
            break;
        }
      });
  }

  //(da decidere)Se tenere la freccia in tutte le pagine oppure solo in quelle presenti nel metodo
  goBack(page: string): void {
    switch (page) {
      case '/handlerPlaypage':
        this.router.navigateByUrl('storJPage');
        break;
      case '/homeStories':
        this.router.navigateByUrl('storJPage');
        break;
      case '/createStory':
        this.router.navigateByUrl('homeStories');
        break;
      case '/formStory':
        this.router.navigateByUrl('homeStories');
        break;
      case '/formScenario':
        this.router.navigateByUrl('createStory');
        break;
      case '/storyObjects':
        this.router.navigateByUrl('createStory');
        break;
      case '/formStoryObject':
        this.router.navigateByUrl('storyObjects');
        break;
      case '/multiplechoice':
        this.router.navigateByUrl('createStory');
        break;
      case '/singlechoice':
        this.router.navigateByUrl('createStory');
        break;
      case '/formMultipleChoice':
        this.router.navigateByUrl('multiplechoice');
        break;
      case '/formSingleChoice':
        this.router.navigateByUrl('singlechoice');
        break;
      default:
        this.router.navigateByUrl('storJPage');
        break;
    }
  }
}