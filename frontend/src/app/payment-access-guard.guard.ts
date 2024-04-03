import { Router, CanActivateFn } from '@angular/router';
import { Injectable } from '@angular/core';
import { UserService } from './services/userservice';


@Injectable({
  providedIn: 'root'
})
export class PaymentAccessGuard {

  constructor(private userService: UserService, private router: Router) {}

  canActivate: CanActivateFn = (route, state) => {
    const currentUser = this.userService.getCurrentUser();
    if (currentUser && !currentUser.statoPagamento) {
      return true;
    } else {
      this.router.navigateByUrl('/storJPage');
      return false;
    }
  }
}
