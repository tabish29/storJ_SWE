CREATE TYPE TIPO_RISPOSTA AS ENUM ('INDOVINELLO', 'MULTIPLA');
CREATE TYPE TIPO_SCENARIO AS ENUM ('INIZIALE', 'NORMALE', 'FINALE');

CREATE TABLE IF NOT EXISTS utente(
	id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    statopagamento BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS storia(
    id SERIAL PRIMARY KEY,
    id_utente INTEGER NOT NULL,
    id_scenario_iniziale INTEGER NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    numero_scenari INTEGER NOT NULL,
    stato_completamento BOOLEAN NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utente(id)
);

CREATE TABLE IF NOT EXISTS scenario(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    id_oggetto_droppato INTEGER NOT NULL,
    testo VARCHAR(255) NOT NULL,
    tipo_risposta TIPO_RISPOSTA NOT NULL,
    tipo_scenario TIPO_SCENARIO NOT NULL
);

CREATE TABLE IF NOT EXISTS oggetto(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id)
);

CREATE TABLE IF NOT EXISTS scelta(
    id SERIAL PRIMARY KEY,
    id_scenario INTEGER NOT NULL,
    id_oggetto_richiesto INTEGER NOT NULL,
    testo VARCHAR(50) NOT NULL,
    id_scenario_successivo INTEGER NOT NULL,
    risposta VARCHAR(50),
    id_scenario_risposta_sbagliata INTEGER,
    FOREIGN KEY (id_scenario) REFERENCES scenario(id),
    FOREIGN KEY (id_oggetto_richiesto) REFERENCES oggetto(id),
    FOREIGN KEY (id_scenario_successivo) REFERENCES scenario(id),
    FOREIGN KEY (id_scenario_risposta_sbagliata) REFERENCES scenario(id)
);

CREATE TABLE IF NOT EXISTS inventario(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    id_utente INTEGER NOT NULL,
    id_oggetto INTEGER NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id),
    FOREIGN KEY (id_utente) REFERENCES utente(id),
    FOREIGN KEY (id_oggetto) REFERENCES oggetto(id)
);

CREATE TABLE IF NOT EXISTS partita(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    id_utente INTEGER NOT NULL,
    id_scenario_corrente INTEGER NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id),
    FOREIGN KEY (id_utente) REFERENCES utente(id),
    FOREIGN KEY (id_scenario_corrente) REFERENCES scenario(id)
);

ALTER TABLE storia ADD CONSTRAINT fk_storia_scenario FOREIGN KEY (id_scenario_iniziale) REFERENCES scenario(id);
ALTER TABLE scenario ADD CONSTRAINT fk_scenario_storia FOREIGN KEY (id_storia) REFERENCES storia(id);
ALTER TABLE scenario ADD CONSTRAINT fk_scenario_oggetto FOREIGN KEY (id_oggetto_droppato) REFERENCES oggetto(id);