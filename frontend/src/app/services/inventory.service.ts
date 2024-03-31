import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { storyObjectService } from './story-object.service';
import { MatchService } from './match.service';
import { inventory } from '../inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private apiServerUrl = 'http://localhost:8080/api/v1';
  private inventorySource = new BehaviorSubject<inventory | null>(this.loadInitialinventory());
  currentInventory = this.inventorySource.asObservable();
  inventoryMap: Map<number, string | undefined> = new Map();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService,private storyObjectService:storyObjectService,private matchService:MatchService) { }

  public getInventoryByMatchId(idMatch: number): Observable<inventory[]> {

    return this.http.get<inventory[]>(this.apiServerUrl + '/partite/' + idMatch + '/oggetti');
  }

  public getinventory(idinventory: number): Observable<inventory> {

    return this.http.get<inventory>(this.apiServerUrl + '/inventari/' + idinventory);
  }

  public addinventory(inventory: inventory): Observable<inventory> {

    return this.http.post<inventory>(this.apiServerUrl + '/inventari', inventory);
  }

  deleteinventory(inventoryId: number): void {
    this.http.delete(this.apiServerUrl + '/inventari/' + inventoryId).subscribe({
      next: () => {
        alert('inventario eliminato con successo');
      },
      error: (error: HttpErrorResponse) => {
        console.error("Errore durante eliminazione del'inventario", error.message);
      }
    });

  }

  loadInitialinventory(): inventory | null {
    return this.localStorageService.getItem('currentInventory');
  }

  changeinventory(newinventory: inventory) {
    this.inventorySource.next(newinventory);
    this.localStorageService.setItem('currentInventory', newinventory);

  }

  getCurrentinventory(): inventory | null {
    return this.inventorySource.value;
  }
}
