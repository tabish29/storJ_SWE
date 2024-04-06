import { Component } from '@angular/core';
import { user } from '../../user';

@Component({
  selector: 'app-stor-jpage',
  templateUrl: './stor-jpage.component.html',
  styleUrl: './stor-jpage.component.css'
})
export class StorJPageComponent {
  currentUser: user | null = null;
  username: String | undefined = '';

  ngOnInit() {
    this.loadCurrentUsername();
  }

  loadCurrentUsername(): void {
    const userJson = localStorage.getItem('currentUser');
    if (userJson) {
      this.currentUser = JSON.parse(userJson);
      this.username = this.currentUser?.username;
    }
  }

}