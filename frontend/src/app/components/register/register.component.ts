import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserService } from '../../userservice';
import { user } from '../../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient,private userService: UserService,private router: Router) { }

  public saveUser(user: user): void{
    this.userService.addUser(user).subscribe(
      (response: user) => {
        alert("Registrazione andata a buon fine, " + response.username + "!")
        //redirect login!
        this.router.navigateByUrl('/login');

      },
      (error: HttpErrorResponse) => {
        if(error.error.code == "UtenteAlreadySigned"){
          alert(error.error.message);
        }else{
          alert(error.error.message);
        }
      }
    );
  }

  onSubmit() {
      
    const userData: user = {
        id: 0,
        username: this.username,
        password: this.password,
        paid: false
    };

    this.saveUser(userData);
  }

}
