import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { user } from '../user';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private userSource = new BehaviorSubject<user | null>(this.loadInitialUser());
  currentUser  = this.userSource.asObservable();

  constructor() { }

  private loadInitialUser(): user | null {
    //recupero i dati dal localStorage e poi li converto nel return
    const userDataJson = localStorage.getItem('currentUser');
    return userDataJson ? JSON.parse(userDataJson) : null;
  }
  
  changeUser(newUser: user | null) {
    this.userSource.next(newUser);
    if (newUser) {
      //per salvare sul localstorage devo convertire in stringa il mio oggetto
      localStorage.setItem('currentUser', JSON.stringify(newUser));
    } else {
      localStorage.removeItem('currentUser');
    }
  }

  getCurrentUser(): user | null {
    return this.userSource.value;
  }

  getCurrentUserName(): String {
    const currentUser = this.getCurrentUser();
    return currentUser ? currentUser.username : ' ';
  }

  // Nel DataService
updateUserPaymentStatus(isPaid: boolean): void {
  const currentUser = this.getCurrentUser();
  if (currentUser) {
    const updatedUser = { ...currentUser, statoPagamento: isPaid }; // Aggiorna lo statoPagamento
    this.changeUser(updatedUser); // Aggiorna l'utente nel BehaviorSubject e in localStorage
  }
}

 
}
