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
    id_creatore INTEGER NOT NULL,
    titolo VARCHAR(255) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    numero_scenari INTEGER NOT NULL,
    stato_completamento BOOLEAN NOT NULL,
    FOREIGN KEY (id_creatore) REFERENCES utente(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS scenario(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    testo VARCHAR(255) NOT NULL,
    tipo_risposta TIPO_RISPOSTA NOT NULL,
    tipo_scenario TIPO_SCENARIO NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS oggetto(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS drop(
    id SERIAL PRIMARY KEY,
    id_scenario INTEGER NOT NULL,
    id_oggetto INTEGER NOT NULL,
    FOREIGN KEY (id_scenario) REFERENCES scenario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_oggetto) REFERENCES oggetto(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS multipla(
    id SERIAL PRIMARY KEY,
    id_scenario INTEGER NOT NULL,
    testo VARCHAR(50) NOT NULL,
    id_scenario_successivo INTEGER NOT NULL,
    FOREIGN KEY (id_scenario) REFERENCES scenario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_scenario_successivo) REFERENCES scenario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS required(
    id SERIAL PRIMARY KEY,
    id_scelta INTEGER NOT NULL,
    id_oggetto INTEGER NOT NULL,
    FOREIGN KEY (id_scelta) REFERENCES multipla(id) ON DELETE CASCADE,
    FOREIGN KEY (id_oggetto) REFERENCES oggetto(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS indovinello(
    id SERIAL PRIMARY KEY,
    id_scenario INTEGER NOT NULL,
    testo VARCHAR(50) NOT NULL,
    risposta VARCHAR(50),
    id_scenario_risposta_corretta INTEGER NOT NULL,
    id_scenario_risposta_sbagliata INTEGER,
    FOREIGN KEY (id_scenario) REFERENCES scenario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_scenario_risposta_corretta) REFERENCES scenario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_scenario_risposta_sbagliata) REFERENCES scenario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS partita(
    id SERIAL PRIMARY KEY,
    id_storia INTEGER NOT NULL,
    id_utente INTEGER NOT NULL,
    id_scenario_corrente INTEGER NOT NULL,
    FOREIGN KEY (id_storia) REFERENCES storia(id) ON DELETE CASCADE,
    FOREIGN KEY (id_utente) REFERENCES utente(id) ON DELETE CASCADE,
    FOREIGN KEY (id_scenario_corrente) REFERENCES scenario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS inventario(
    id SERIAL PRIMARY KEY,
    id_partita INTEGER NOT NULL,
    id_oggetto INTEGER NOT NULL,
    FOREIGN KEY (id_partita) REFERENCES partita(id) ON DELETE CASCADE,
    FOREIGN KEY (id_oggetto) REFERENCES oggetto(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_numero_scenari()
    RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        UPDATE storia
        SET numero_scenari = numero_scenari + 1
        WHERE id = NEW.id_storia;
    ELSIF TG_OP = 'DELETE' THEN
        UPDATE storia
        SET numero_scenari = numero_scenari - 1
        WHERE id = OLD.id_storia;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER increment_numero_scenari_trigger
    AFTER INSERT ON scenario
    FOR EACH ROW
EXECUTE FUNCTION update_numero_scenari();

CREATE TRIGGER decrement_numero_scenari_trigger
    AFTER DELETE ON scenario
    FOR EACH ROW
EXECUTE FUNCTION update_numero_scenari();