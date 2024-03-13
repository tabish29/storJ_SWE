import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StorJPageComponent } from './components/stor-jpage/stor-jpage.component';
import { PaymentPageComponent } from './components/payment-page/payment-page.component';
import { accessGuard } from './access.guard';
import { HandlerPlaypageComponent } from './components/handler-playpage/handler-playpage.component';
import { CreateStoryComponent } from './components/create-story/create-story.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'storJPage',
    component: StorJPageComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'payment',
    component: PaymentPageComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'handlerPlaypage',
    component: HandlerPlaypageComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'createStory',
    component: CreateStoryComponent,
    canActivate: [accessGuard]
  },
  {
    path: '', 
    redirectTo: 'home',
     pathMatch: 'full'
  },
  {
    //tutti i path che non rientrano tra quelli specificati sopra
    path: '**', 
    component: NotFoundComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
