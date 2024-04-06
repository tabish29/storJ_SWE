import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../services/userservice';
import { user } from '../../user';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient, private userService: UserService, private authService: AuthService, private router: Router) { }

  getUser(): void {
    this.userService.getUserByUsername(this.username).subscribe(
      (response: user) => {
        if (response.password === this.password) {
          this.authService.setAuthStatus(true);
          this.userService.changeUser(response);
          this.router.navigateByUrl('/storJPage');
        } else {
          this.authService.setAuthStatus(false);
          alert("Credenziali di accesso errate!");
        }
      },
      (error: HttpErrorResponse) => {
        if (error.error.code == "UtenteNotFound") {
          this.authService.setAuthStatus(false);
          alert(error.error.message);
        } else {
          this.authService.setAuthStatus(false);
          alert("Riempi i campi prima di procedere!");
        }
      }
    );
  }

  onLogin() {
    this.getUser();
  }

}
