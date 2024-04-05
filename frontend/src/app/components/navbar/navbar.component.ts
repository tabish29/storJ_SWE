import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Subscription, throwError } from 'rxjs';
import { catchError, filter } from 'rxjs/operators';
import { StoryService } from '../../services/story.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SingleChoiceService } from '../../services/single-choice.service';
import { LocalStorageService } from '../../services/local-storage.service';
import { InventoryService } from '../../services/inventory.service';
import { MatchService } from '../../services/match.service';
import { inventory } from '../../inventory';
import { PopupComponent } from '../popup/popup.component';
import { storyObject } from '../../storyObject';


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
  scenarioId!: number;
  isInTextEditMode!: boolean;
  inventoryItems!: storyObject[];
  popUp?: PopupComponent

  constructor(private router: Router, private authService: AuthService, private userService: UserService, private storyService: StoryService, private singleChoiceService: SingleChoiceService, private localStorageService: LocalStorageService, private matchService: MatchService, private inventoryService: InventoryService, private dialog: MatDialog) {
    this.currentUrl = router.url;
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }

  ngOnInit() {
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
        this.showPaymentButton = false;
      }
    }, error => {
      console.log('error:', error);
    });

    const currentscenario = this.localStorageService.getItem('currentScenario');
    if (currentscenario) {
      this.scenarioId = currentscenario.id;
      this.isSingleChoiceCreated(this.scenarioId);
    }

    this.isInTextEditMode = this.storyService.isStoryCompleted();
    console.log("valore dell'url current: " + this.currentUrl);
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Reindirizza l'utente alla pagina di login dopo il logout
  }

  getIsSingleChoiceCreated(): boolean {
    return this.singleChoiceService.getIsChoiceCreated();
  }

  isSingleChoiceCreated(scenarioID: number): void {
    if (scenarioID) {
      this.singleChoiceService.getSingleChoiceByScenarioId(scenarioID).subscribe({
        next: (response) => {
          if (response) {
            this.singleChoiceService.setIsChoiceCreated(true);
          } else {
            this.singleChoiceService.setIsChoiceCreated(false);
          }
        },
        error: (error) => {
          console.error('Errore nel metodo getSingleChoiceByScenarioId: ', error);
          this.singleChoiceService.setIsChoiceCreated(false);
        }
      });
    } else {
      console.error("Non c'è un id dello scenario corrente");
    }
  }

  saveStory(): void {
    this.storyService.saveStory().subscribe(
      (response: any) => {
        alert('salvataggio avvenuto con successo')
        this.router.navigateByUrl('/homeStories');
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
      }
    );
  }

  async openInventory() {
    const currentMatch = this.matchService.getCurrentMatch();

    if (currentMatch) {
      await this.inventoryService.getInventoryByMatchId(currentMatch.id).subscribe({
        next: (response) => {

          if (response) {
            this.inventoryItems = response;
            this.dialog.open(PopupComponent, {
              data: {
                showPreview: false,
                inventoryItems: this.inventoryItems
              }
            });
            
          } else {
            console.log("non ci sono oggetti nell'inventario");
          }
        },
        error: (error) => {
          console.error('Errore nel metodo getSingleChoiceByScenarioId: ', error);
          this.singleChoiceService.setIsChoiceCreated(false);
        }
      });

    };

  }

  goBack(page: string): void {
    switch (page) {
      case '/handlerPlaypage':
        this.router.navigateByUrl('storJPage');
        break;
      case '/playPage':
        this.router.navigateByUrl('handlerPlaypage');
        break;
      case '/match':
        this.router.navigateByUrl('handlerPlaypage');
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