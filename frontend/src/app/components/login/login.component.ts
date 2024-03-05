import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router'; 
import { error } from 'console';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginObj: Login;
  errorMessage: string = '';

  constructor(private http: HttpClient,private router: Router) {
    this.loginObj = new Login();
  }

  onLogin() {
    //Mettere l'indirizzo dell'api di Davide
    this.http.get('http://localhost:8080/api/v1/utenti/{username}').subscribe((res:any)=>{
      if(res.result) {
        alert("Login Success");
        localStorage.setItem('angular17token', res.data.token)
        this.router.navigateByUrl('/home')
      } else {
        alert(res.message)
      }
    },error =>{
      //gestione errori
      console.error('Errore durante la richiesta GET:', error);
      this.errorMessage = 'Si è verificato un errore durante la verifica dell\'utente.';
    });
    
  }

  fetchDataPost(username: string, password: string): Observable<any> {
    // Definisci l'URL dell'API
    const apiUrl ='http://localhost:8080/api/v1/utenti'; // Sostituisci con il tuo URL API

    // Definisci i dati da inviare
    const formData = {
      username: username,
      password: password
    };

    // Imposta le intestazioni per il tipo di contenuto JSON
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    // Invia la richiesta POST all'API
    return this.http.post<any>(apiUrl, formData, httpOptions);
  }



    
  }

  export class Login { 
    username: string;
    Password: string;
    constructor() {
      this.username = '';
      this.Password = '';
    } 
  }