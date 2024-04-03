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
import { HomeStoriesComponent } from './components/home-stories/home-stories.component';
import { FormStoryComponent } from './components/form-story/form-story.component';
import { FormScenarioComponent } from './components/form-scenario/form-scenario.component';
import { StoryObjectsComponent } from './components/story-objects/story-objects.component';
import { FormStoryObjectComponent } from './components/form-story-object/form-story-object.component';
import { MultipleChoiceComponent } from './components/multiple-choice/multiple-choice.component';
import { SingleChoiceComponent } from './components/single-choice/single-choice.component';
import { FormMultipleChoiceComponent } from './components/form-multiple-choice/form-multiple-choice.component';
import { FormSingleChoiceComponent } from './components/form-single-choice/form-single-choice.component';
import { PlayPageComponent } from './components/play-page/play-page.component';
import { MatchComponent } from './components/match/match.component';
import { PaymentAccessGuard } from './payment-access-guard.guard';


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
    canActivate: [accessGuard,PaymentAccessGuard]
  },
  {
    path: 'homeStories',
    component: HomeStoriesComponent,
    canActivate: [accessGuard]
  },

  {
    path: 'handlerPlaypage',
    component: HandlerPlaypageComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'formStory',
    component: FormStoryComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'createStory',
    component: CreateStoryComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'formScenario',
    component: FormScenarioComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'storyObjects',
    component: StoryObjectsComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'formStoryObject',
    component: FormStoryObjectComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'multiplechoice',
    component: MultipleChoiceComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'formMultipleChoice',
    component: FormMultipleChoiceComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'formSingleChoice',
    component: FormSingleChoiceComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'singlechoice',
    component: SingleChoiceComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'playPage',
    component: PlayPageComponent,
    canActivate: [accessGuard]
  },
  {
    path: 'match',
    component: MatchComponent,
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