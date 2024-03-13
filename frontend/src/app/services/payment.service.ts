import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentInfo } from '../paymentInfo';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiServerUrl='http://localhost:8080/api/v1';
  

  constructor(private http: HttpClient,private dataService: DataService) { }
  
  public doPayment(paymentInfo: PaymentInfo): Observable<PaymentInfo>{
    const currentUsername:String=this.dataService.getCurrentUserName();
    return this.http.post<PaymentInfo>(this.apiServerUrl + '/utenti/'+currentUsername+'/pagamento', paymentInfo);
  }

}
