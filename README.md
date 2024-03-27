# storJ
Progetto per il corso di Ingegneria del Software, Informatica per il Management anno 2023/2024

### Realizzato dagli studenti:
- Canghiari Matteo
- De Rosa Davide
- Ghazanfar Tabish
- Nadifi Ossama

[Specifiche tecniche del progetto](https://docs.google.com/document/d/e/2PACX-1vQT60002z4ymBNlmkg-5S2xZ4z1RUGPX2GqMxegJK7sw6_dsQEfgzr_lN7A0EqXwLioOlCCS_aSB3rO/pub)

## Informazioni sullo stack
**storJ** si avvale di un insieme di tecnologie moderne e scalabili per garantire una solida base per lo sviluppo e il deployment dell'applicativo. Lo stack tecnologico include:

### Frontend:

- **Angular**: viene utilizzato Angular per la parte frontend, fornendo interfacce utente dinamiche e reattive 

### Backend:

- **Spring Boot**: viene utilizzato il framework Spring per la realizzazione del backend. Vengono esposte diverse __REST API__, realizzate secondo lo standard __OpenAPI 3.0__, con approccio __Contract-First__

- **Database**: viene utilizzato PostgreSQL per la memorizzazione dei dati

### Infrastruttura e Deployment:

- **Git**: per il controllo di versione del codice sorgente, viene utilizzato Git insieme a GitHub per la collaborazione e la gestione dei rami di sviluppo

- **Docker**: viene adottato Docker per il deployment dell'applicazione, garantendo un ambiente consistente e isolato per l'esecuzione dei diversi servizi

- **Docker Compose**: per la gestione dei container Docker e l'orchestrazione dei diversi servizi, viene utilizzato Docker Compose, semplificando il processo di deployment e gestendo le dipendenze tra i diversi componenti dell'applicazione

- **Jenkins**: viene utilizzato Jenkins per automatizzare i processi di **Continuous Integration (CI)** e **Continuous Deployment (CD)**

## Deploy
Unico requisito per il deploy dell'applicativo è [Docker](https://www.docker.com/get-started/).

Scaricata la repository, eseguire all'interno della cartella il comando
```bash
docker-compose up --build
```

Una volta avviati i diversi servizi, sarà possibile accedere alla piattaforma tramite il link
```bash
http://localhost:4201/
```

Per terminare l'esecuzione, eseguire all'interno della cartella il comando
```bash
docker-compose down
```