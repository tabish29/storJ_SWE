export class singleChoice {
    constructor(
        public id: number,
        public id_scenario: number,
        public testo: string,
        public risposta: string,
        public id_scenario_risposta_corretta: number,
        public id_scenario_risposta_sbagliata: number
    ) {}
}
