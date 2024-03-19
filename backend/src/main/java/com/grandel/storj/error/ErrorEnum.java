package com.grandel.storj.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    UTENTENOTFOUND("UtenteNotFound", "Utente non registrato!", 404),
    UTENTEALREADYSIGNED("UtenteAlreadySigned", "Utente già registrato!", 400),
    UTENTEALREADYPAID("UtenteAlreadyPaid", "Utente ha già effettuato il pagamento!", 400),
    PAYMENTFAILED("PaymentFailed", "Pagamento non andato a buon fine!", 400),
    STORIANOTFOUND("StoriaNotFound", "Storia non trovata!", 404),
    FILTROERROR("FiltroError", "Errore nei filtri!", 400),
    SCENARIONOTFOUND("ScenarioNotFound", "Scenario non trovato!", 404),
    OGGETTONOTFOUND("OggettoNotFound", "Oggetto non trovato!", 404),
    ENUMERROR("EnumError", "Valore errato negli ENUM!", 400),
    DROPNOTFOUND("DropNotFound", "Drop non trovato!", 404),
    MULTIPLANOTFOUND("MultiplaNotFound", "Multipla non trovata!", 404),
    INDOVINELLONOTFOUND("IndovinelloNotFound", "Indovinello non trovato!", 404),
    REQUIREDNOTFOUND("RequiredNotFound", "Required non trovato!", 404),
    PARTITANOTFOUND("PartitaNotFound", "Partita non trovata!", 404),
    INVENTARIONOTFOUND("InventarioNotFound", "Inventario non trovato!", 404),
    SALVATAGGIOFAILEDSCIN("SalvataggioFailedScenarioIniziale", "Salvataggio storia fallito. Non sono presenti scenari iniziali, o ne è presente più di uno!", 400),
    SALVATAGGIOFAILEDSCFI("SalvataggioFailedScenarioFinale", "Salvataggio storia fallito. Devono esserci degli scenari finali!", 400),
    SALVATAGGIOFAILEDMULT("SalvataggioFailedMultipla", "Salvataggio storia fallito. In ogni scenario multiplo devono essere presenti almeno due scelte!", 400),
    SALVATAGGIOFAILEDINDO("SalvataggioFailedIndovinello", "Salvataggio storia fallito. In ogni scenario indovinello deve essere presente una scelta!", 400);
    
    private String code;
    private String message;
    private int httpStatus;
}
