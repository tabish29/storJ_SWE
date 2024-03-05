import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  username: string = '';
  password: string = '';
  registrationMessage: string = '';
  httpClient= inject(HttpClient);
  data: any[]=[];

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(){
    this.httpClient.get("http://localhost:8080/api/v1/utenti/davide").subscribe((data:any) =>{console.log(data); this.data=data});
  }

}
