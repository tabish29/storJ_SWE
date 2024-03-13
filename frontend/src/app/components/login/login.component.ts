import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { DataService } from '../../services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  
  constructor(private http: HttpClient,private userService: UserService,private authService:AuthService, private dataService: DataService,private router: Router) { }
  public getUser(): void {
    this.userService.getUserByUsername(this.username).subscribe(
      (response: user) => {
        if(response.password === this.password){
          this.authService.setAuthStatus(true);
          this.dataService.changeUser(response); 
          alert("Ciao " + response.username);
          //redirect to pagina storJPage!
          this.router.navigateByUrl('/storJPage'); // Usa il Router per il redirect
        }else{
          this.authService.setAuthStatus(false);
          alert("Username o Password errata!");
        }
      },
      (error: HttpErrorResponse) => {
        if(error.error.code == "UtenteNotFound"){
          this.authService.setAuthStatus(false);
        alert(error.error.message);
        }else{
          this.authService.setAuthStatus(false);
          alert(error.error.message);
        }
      }
    );
  }

  onLogin() {
    this.getUser();
  }
}
