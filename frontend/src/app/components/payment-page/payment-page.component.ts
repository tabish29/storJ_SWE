import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentService } from '../../services/payment.service';
import { PaymentInfo } from '../../paymentInfo';
import { UserService } from '../../services/userservice';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrl: './payment-page.component.css'
})
export class PaymentPageComponent {
  amount: number=0;
  cardHolder: string='';
  cardNumber: string='';
  cvv: string='';

  constructor(private http: HttpClient,private paymentService: PaymentService,private router: Router,private userService:UserService) { }

  public savePayment(paymentInfo: PaymentInfo): void{
    this.paymentService.doPayment(paymentInfo).subscribe(
      (response: PaymentInfo) => {
        alert("Pagamento andato a buon fine")
        console.log(response)

        // Aggiorna lo stato di pagamento dell'utente a true
        this.userService.updateUserPaymentStatus(true);
        
        this.router.navigateByUrl('/storJPage');

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
      
    const paymentData: PaymentInfo = {
        amount: this.amount,
        cardHolder: this.cardHolder,
        cardNumber: this.cardNumber,
        cvv: this.cvv,
    };

    this.savePayment(paymentData);
    }

  }