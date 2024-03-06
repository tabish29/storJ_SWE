import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentInfo } from '../paymentInfo';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiServerUrl='http://localhost:8080/api/v1';

  constructor(private http: HttpClient) { }
  
  public doPayment(paymentInfo: PaymentInfo): Observable<PaymentInfo>{
    //devo mettere nel metodo username string e sostituirlo a davide nell'url
    return this.http.post<PaymentInfo>(this.apiServerUrl + '/utenti/davide/pagamento', paymentInfo);
  }

}
