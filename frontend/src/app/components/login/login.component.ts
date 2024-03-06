import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  
  constructor(private http: HttpClient,private userService: UserService,private router: Router) { }
  public getUser(): void {
    this.userService.getUserByUsername(this.username).subscribe(
      (response: user) => {
        if(response.password === this.password){
          alert("Ciao " + response.username);
          //redirect to pagina home!
          this.router.navigateByUrl('/storJPage'); // Usa il Router per il redirect
        }else{
          alert("Username o Password errata!");
        }
      },
      (error: HttpErrorResponse) => {
        if(error.error.code == "UtenteNotFound"){
        alert(error.error.message);
        }else{
          alert(error.error.message);
        }
      }
    );
  }

  onLogin() {
    this.getUser();
  }
}
