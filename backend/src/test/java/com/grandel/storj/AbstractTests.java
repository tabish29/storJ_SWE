package com.grandel.storj;

import com.grandel.storj.dto.*;
import com.grandel.storj.entity.*;
import com.grandel.storj.mapper.*;
import com.grandel.storj.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import storj.model.*;

@SpringBootTest
public class AbstractTests {

    @Autowired
    public UtenteMapper utenteMapper;
    @Autowired
    public UtenteRepository utenteRepository;
    @Autowired
    public StoriaMapper storiaMapper;
    @Autowired
    public StoriaRepository storiaRepository;
    @Autowired
    public OggettoMapper oggettoMapper;
    @Autowired
    public OggettoRepository oggettoRepository;
    @Autowired
    public ScenarioMapper scenarioMapper;
    @Autowired
    public ScenarioRepository scenarioRepository;
    @Autowired
    public DropMapper dropMapper;
    @Autowired
    public DropRepository dropRepository;
    @Autowired
    public MultiplaMapper multiplaMapper;
    @Autowired
    public MultiplaRepository multiplaRepository;
    @Autowired
    public IndovinelloMapper indovinelloMapper;
    @Autowired
    public IndovinelloRepository indovinelloRepository;
    @Autowired
    public RequiredMapper requiredMapper;
    @Autowired
    public RequiredRepository requiredRepository;
    @Autowired
    public PartitaMapper partitaMapper;
    @Autowired
    public PartitaRepository partitaRepository;
    @Autowired
    public InventarioMapper inventarioMapper;
    @Autowired
    public InventarioRepository inventarioRepository;

    void drop() {
        utenteRepository.deleteAll();
        storiaRepository.deleteAll();
        oggettoRepository.deleteAll();
        scenarioRepository.deleteAll();
        dropRepository.deleteAll();
        multiplaRepository.deleteAll();
        indovinelloRepository.deleteAll();
        requiredRepository.deleteAll();
        partitaRepository.deleteAll();
        inventarioRepository.deleteAll();
    }

    public Utente creaUtenteModel(Long id, String username, String password, boolean statoPagamento) {
        Utente utente = new Utente();
        utente.setId(id);
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setStatoPagamento(statoPagamento);
        return utente;
    }

    public UtenteDTO creaUtenteDTO(String username, String password, boolean statoPagamento) {
        UtenteDTO utente = new UtenteDTO();
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setStatoPagamento(statoPagamento);
        return utente;
    }

    public UtenteEntity creaUtenteEntity(Long id, String username, String password, boolean statoPagamento) {
        UtenteEntity utente = new UtenteEntity();
        utente.setId(id);
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setStatoPagamento(statoPagamento);
        return utente;
    }

    public Storia creaStoriaModel(Long id, Long idCreatore, String titolo, String categoria, int numSc, boolean statoCompletamento) {
        Storia storia = new Storia();
        storia.setId(id);
        storia.setIdCreatore(idCreatore);
        storia.setTitolo(titolo);
        storia.setCategoria(categoria);
        storia.setNumeroScenari(numSc);
        storia.setStatoCompletamento(statoCompletamento);
        return storia;
    }

    public StoriaDTO creaStoriaDTO(UtenteEntity idCreatore, String titolo, String categoria, int numSc, boolean statoCompletamento) {
        StoriaDTO storia = new StoriaDTO();
        storia.setIdCreatore(idCreatore);
        storia.setTitolo(titolo);
        storia.setCategoria(categoria);
        storia.setNumeroScenari(numSc);
        storia.setStatoCompletamento(statoCompletamento);
        return storia;
    }

    public StoriaEntity creaStoriaEntity(Long id, UtenteEntity idCreatore, String titolo, String categoria, int numSc, boolean statoCompletamento) {
        StoriaEntity storia = new StoriaEntity();
        storia.setId(id);
        storia.setIdCreatore(idCreatore);
        storia.setTitolo(titolo);
        storia.setCategoria(categoria);
        storia.setNumeroScenari(numSc);
        storia.setStatoCompletamento(statoCompletamento);
        return storia;
    }

    public Oggetto creaOggettoModel(Long id, Long idStoria, String nome, String descrizione) {
        Oggetto oggetto = new Oggetto();
        oggetto.setId(id);
        oggetto.setIdStoria(idStoria);
        oggetto.setNome(nome);
        oggetto.setDescrizione(descrizione);
        return oggetto;
    }

    public OggettoDTO creaOggettoDTO(StoriaEntity idStoria, String nome, String descrizione) {
        OggettoDTO oggettoDTO = new OggettoDTO();
        oggettoDTO.setIdStoria(idStoria);
        oggettoDTO.setNome(nome);
        oggettoDTO.setDescrizione(descrizione);
        return oggettoDTO;
    }

    public OggettoEntity creaOggettoEntity(Long id, StoriaEntity idStoria, String nome, String descrizione) {
        OggettoEntity oggetto = new OggettoEntity();
        oggetto.setId(id);
        oggetto.setIdStoria(idStoria);
        oggetto.setNome(nome);
        oggetto.setDescrizione(descrizione);
        return oggetto;
    }

    public Scenario creaScenarioModel(Long id, Long idStoria, String testo, TipoRispostaEnum tipoRisposta, TipoScenarioEnum tipoScenario) {
        Scenario scenario = new Scenario();
        scenario.setId(id);
        scenario.setIdStoria(idStoria);
        scenario.setTesto(testo);
        scenario.setTipoRisposta(tipoRisposta);
        scenario.setTipoScenario(tipoScenario);
        return scenario;
    }

    public ScenarioDTO creaScenarioDTO(StoriaEntity idStoria, String testo, TipoRispostaEnum tipoRisposta, TipoScenarioEnum tipoScenario) {
        ScenarioDTO scenario = new ScenarioDTO();
        scenario.setIdStoria(idStoria);
        scenario.setTesto(testo);
        scenario.setTipoRisposta(tipoRisposta);
        scenario.setTipoScenario(tipoScenario);
        return scenario;
    }

    public ScenarioEntity creaScenarioEntity(Long id, StoriaEntity idStoria, String testo, ScenarioEntity.TipoRispostaEnum tipoRisposta, ScenarioEntity.TipoScenarioEnum tipoScenario) {
        ScenarioEntity scenario = new ScenarioEntity();
        scenario.setId(id);
        scenario.setIdStoria(idStoria);
        scenario.setTesto(testo);
        scenario.setTipoRisposta(tipoRisposta);
        scenario.setTipoScenario(tipoScenario);
        return scenario;
    }

    public Drop creaDropModel(Long id, Long idScenario, Long idOggetto) {
        Drop drop = new Drop();
        drop.setId(id);
        drop.setIdScenario(idScenario);
        drop.setIdOggetto(idOggetto);
        return drop;
    }

    public DropDTO creaDropDTO(ScenarioEntity idScenario, OggettoEntity idOggetto) {
        DropDTO drop = new DropDTO();
        drop.setIdScenario(idScenario);
        drop.setIdOggetto(idOggetto);
        return drop;
    }

    public DropEntity creaDropEntity(Long id, ScenarioEntity idScenario, OggettoEntity idOggetto) {
        DropEntity drop = new DropEntity();
        drop.setId(id);
        drop.setIdScenario(idScenario);
        drop.setIdOggetto(idOggetto);
        return drop;
    }

    public Multipla creaMultiplaModel(Long id, Long idScenario, String testo, Long idScenarioSucc) {
        Multipla multipla = new Multipla();
        multipla.setId(id);
        multipla.setIdScenario(idScenario);
        multipla.setTesto(testo);
        multipla.setIdScenarioSuccessivo(idScenarioSucc);
        return multipla;
    }

    public MultiplaDTO creaMultiplaDTO(ScenarioEntity idScenario, String testo, ScenarioEntity idScenarioSucc) {
        MultiplaDTO multipla = new MultiplaDTO();
        multipla.setIdScenario(idScenario);
        multipla.setTesto(testo);
        multipla.setIdScenarioSuccessivo(idScenarioSucc);
        return multipla;
    }

    public MultiplaEntity creaMultiplaEntity(Long id, ScenarioEntity idScenario, String testo, ScenarioEntity idScenarioSucc) {
        MultiplaEntity multipla = new MultiplaEntity();
        multipla.setId(id);
        multipla.setIdScenario(idScenario);
        multipla.setTesto(testo);
        multipla.setIdScenarioSuccessivo(idScenarioSucc);
        return multipla;
    }

    public Indovinello creaIndovinelloModel(Long id, Long idScenario, String testo, String risposta, Long idScenarioCorretta, Long idScenarioSbagliato) {
        Indovinello indovinello = new Indovinello();
        indovinello.setId(id);
        indovinello.setIdScenario(idScenario);
        indovinello.setTesto(testo);
        indovinello.setRisposta(risposta);
        indovinello.setIdScenarioRispostaCorretta(idScenarioCorretta);
        indovinello.setIdScenarioRispostaSbagliata(idScenarioSbagliato);
        return indovinello;
    }

    public IndovinelloDTO creaIndovinelloDTO(ScenarioEntity idScenario, String testo, String risposta, ScenarioEntity idScenarioCorretta, ScenarioEntity idScenarioSbagliato) {
        IndovinelloDTO indovinello = new IndovinelloDTO();
        indovinello.setIdScenario(idScenario);
        indovinello.setTesto(testo);
        indovinello.setRisposta(risposta);
        indovinello.setIdScenarioRispostaCorretta(idScenarioCorretta);
        indovinello.setIdScenarioRispostaSbagliata(idScenarioSbagliato);
        return indovinello;
    }

    public IndovinelloEntity creaIndovinelloEntity(Long id, ScenarioEntity idScenario, String testo, String risposta, ScenarioEntity idScenarioCorretta, ScenarioEntity idScenarioSbagliato) {
        IndovinelloEntity indovinello = new IndovinelloEntity();
        indovinello.setId(id);
        indovinello.setIdScenario(idScenario);
        indovinello.setTesto(testo);
        indovinello.setRisposta(risposta);
        indovinello.setIdScenarioRispostaCorretta(idScenarioCorretta);
        indovinello.setIdScenarioRispostaSbagliata(idScenarioSbagliato);
        return indovinello;
    }

    public Required creaRequiredModel(Long id, Long idScelta, Long idOggetto) {
        Required required = new Required();
        required.setId(id);
        required.setIdScelta(idScelta);
        required.setIdOggetto(idOggetto);
        return required;
    }

    public RequiredDTO creaRequiredDTO(MultiplaEntity idScelta, OggettoEntity idOggetto) {
        RequiredDTO requiredDTO = new RequiredDTO();
        requiredDTO.setIdScelta(idScelta);
        requiredDTO.setIdOggetto(idOggetto);
        return requiredDTO;
    }

    public RequiredEntity creaRequiredEntity(Long id, MultiplaEntity idScelta, OggettoEntity idOggetto) {
        RequiredEntity required = new RequiredEntity();
        required.setId(id);
        required.setIdScelta(idScelta);
        required.setIdOggetto(idOggetto);
        return required;
    }

    public Partita creaPartitaModel(Long id, Long idStoria, Long idUtente, Long idScenarioCorrente) {
        Partita partita = new Partita();
        partita.setId(id);
        partita.setIdStoria(idStoria);
        partita.setIdUtente(idUtente);
        partita.setIdScenarioCorrente(idScenarioCorrente);
        return partita;
    }

    public PartitaDTO creaPartitaDTO(StoriaEntity idStoria, UtenteEntity idUtente, ScenarioEntity idScenarioCorrente) {
        PartitaDTO partita = new PartitaDTO();
        partita.setIdStoria(idStoria);
        partita.setIdUtente(idUtente);
        partita.setIdScenarioCorrente(idScenarioCorrente);
        return partita;
    }

    public PartitaEntity creaPartitaEntity(Long id, StoriaEntity idStoria, UtenteEntity idUtente, ScenarioEntity idScenarioCorrente) {
        PartitaEntity partita = new PartitaEntity();
        partita.setId(id);
        partita.setIdStoria(idStoria);
        partita.setIdUtente(idUtente);
        partita.setIdScenarioCorrente(idScenarioCorrente);
        return partita;
    }

    public Inventario creaInventarioModel(Long id, Long idPartita, Long idOggetto) {
        Inventario inventario = new Inventario();
        inventario.setId(id);
        inventario.setIdPartita(idPartita);
        inventario.setIdOggetto(idOggetto);
        return inventario;
    }

    public InventarioDTO creaInventarioDTO(PartitaEntity idPartita, OggettoEntity idOggetto) {
        InventarioDTO inventario = new InventarioDTO();
        inventario.setIdPartita(idPartita);
        inventario.setIdOggetto(idOggetto);
        return inventario;
    }

    public InventarioEntity creaInventarioEntity(Long id, PartitaEntity idPartita, OggettoEntity idOggetto) {
        InventarioEntity inventario = new InventarioEntity();
        inventario.setId(id);
        inventario.setIdPartita(idPartita);
        inventario.setIdOggetto(idOggetto);
        return inventario;
    }
}
