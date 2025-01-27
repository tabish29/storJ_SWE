import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LocalStorageService } from '../../services/local-storage.service';

@Component({
  selector: 'app-language-selector',
  templateUrl: './language-selector.component.html',
  styleUrl: './language-selector.component.css'
})
export class LanguageSelectorComponent {
  selectedLanguage: string;

  languages = [
    { code: 'en', name: 'English' },
    { code: 'it', name: 'Italiano' },
    { code: 'fr', name: 'Français' },
    { code: 'de', name: 'Deutsch' },
    { code: 'es', name: 'Espanol' },
  ];

  constructor(private translate: TranslateService, private localStorageService: LocalStorageService) {
    const savedLanguage = this.localStorageService.getItem('selectedLanguage');
    if (savedLanguage) {
      this.selectedLanguage = savedLanguage; 
    } else {
      this.selectedLanguage = 'it'; // Lingua di default
    }

    // Imposta la lingua di default
    this.translate.setDefaultLang(this.selectedLanguage);
    this.translate.use(this.selectedLanguage);
  }

  switchLanguage(language: string) {
    this.selectedLanguage = language;
    this.translate.use(language);

    this.localStorageService.setItem('selectedLanguage', language);
  }

}
