import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentInfo } from '../paymentInfo';
import { UserService } from './userservice';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiServerUrl = 'http://localhost:8080/api/v1';


  constructor(private http: HttpClient, private userService: UserService) { }

  public doPayment(paymentInfo: PaymentInfo): Observable<PaymentInfo> {
    const currentUsername: String = this.userService.getCurrentUserName();
    return this.http.post<PaymentInfo>(this.apiServerUrl + '/utenti/' + currentUsername + '/pagamento', paymentInfo);
  }

}
