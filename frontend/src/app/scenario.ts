import { responseType } from "./responseType";
import { scenarioType } from "./scenarioType";

export class scenario {

    constructor(
        public id: number,
        public id_storia: number,
        public testo: string,
        public tipo_risposta: responseType,
        public tipo_scenario: scenarioType
    ) { }

}