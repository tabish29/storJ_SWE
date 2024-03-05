import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient) { }

  onSubmit() {
      
    const userData = {
        username: this.username,
        password: this.password
    };

    this.http.post('http://localhost:8080/api/v1/utenti', userData)
        .subscribe((data) => {
            console.log('Risposta dal server:', data);
            // Gestisci la risposta dal server come desiderato
        }, (error) => {
            console.error('Errore durante la richiesta:', error);
            // Gestisci eventuali errori durante la richiesta
        });
  }

}
