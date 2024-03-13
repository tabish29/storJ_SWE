import { Component } from '@angular/core';
import { stories } from '../../stories';

@Component({
  selector: 'app-handler-playpage',
  templateUrl: './handler-playpage.component.html',
  styleUrl: './handler-playpage.component.css'
})

//QUANDO CLICCI GIOCA CHIAMATA API PER PRENDERE TUTTE LE STORIE, DA CUI SI PRENDERANNO NOME, AUTORE, NUMERO SCENARI E BOTTONE --> GIOCA

export class HandlerPlaypageComponent {
  //array di storie con dati fittizi
  stories= [
                {
                  id: 1,
                  id_creatore: 1, 
                  id_scenario_iniziale: 1, 
                  categoria: 'thriller',
                  numero_scenari: 12,
                  statoCompletamento: false
                },
                {
                  id: 2,
                  id_creatore: 2, 
                  id_scenario_iniziale: 5, 
                  categoria: 'avventura',
                  numero_scenari: 8,
                  statoCompletamento: true
                },
                {
                  id: 3,
                  id_creatore: 3, 
                  id_scenario_iniziale: 2, 
                  categoria: 'fantasy',
                  numero_scenari: 15,
                  statoCompletamento: false
                },
                {
                  id: 4,
                  id_creatore: 4, 
                  id_scenario_iniziale: 3, 
                  categoria: 'sci-fi',
                  numero_scenari: 10,
                  statoCompletamento: true
                },
                {
                  id: 5,
                  id_creatore: 5, 
                  id_scenario_iniziale: 4, 
                  categoria: 'horror',
                  numero_scenari: 6,
                  statoCompletamento: false
                },
                {
                  id: 6,
                  id_creatore: 1, 
                  id_scenario_iniziale: 6, 
                  categoria: 'giallo',
                  numero_scenari: 9,
                  statoCompletamento: true
                },
                {
                  id: 7,
                  id_creatore: 2, 
                  id_scenario_iniziale: 7, 
                  categoria: 'storico',
                  numero_scenari: 5,
                  statoCompletamento: false
                },
                {
                  id: 8,
                  id_creatore: 3, 
                  id_scenario_iniziale: 8, 
                  categoria: 'azione',
                  numero_scenari: 11,
                  statoCompletamento: true
                },
                {
                  id: 9,
                  id_creatore: 4, 
                  id_scenario_iniziale: 9, 
                  categoria: 'mistero',
                  numero_scenari: 7,
                  statoCompletamento: false
                },
                {
                  id: 10,
                  id_creatore: 5, 
                  id_scenario_iniziale: 10, 
                  categoria: 'romantico',
                  numero_scenari: 4,
                  statoCompletamento: true
                }
              ];
}