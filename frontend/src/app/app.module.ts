import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';

import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StorJPageComponent } from './components/stor-jpage/stor-jpage.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { PaymentPageComponent } from './components/payment-page/payment-page.component';
import { CreateStoryComponent } from './components/create-story/create-story.component';
import { HandlerPlaypageComponent } from './components/handler-playpage/handler-playpage.component';
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
import { PopupComponent } from './components/popup/popup.component';
import { MatchComponent } from './components/match/match.component';
import { LanguageSelectorComponent } from './components/language-selector/language-selector.component';

// Funzione per il loader delle traduzioni
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavbarComponent,
    NotFoundComponent,
    StorJPageComponent,
    PaymentPageComponent,
    CreateStoryComponent,
    HandlerPlaypageComponent,
    HomeStoriesComponent,
    FormStoryComponent,
    FormScenarioComponent,
    StoryObjectsComponent,
    FormStoryObjectComponent,
    MultipleChoiceComponent,
    SingleChoiceComponent,
    FormMultipleChoiceComponent,
    FormSingleChoiceComponent,
    PlayPageComponent,
    PopupComponent,
    MatchComponent,
    LanguageSelectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatTooltipModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),

  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }