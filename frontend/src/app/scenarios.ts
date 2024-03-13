export class scenario {
    constructor(
        id:number,
        id_storia:number,
        id_oggetto_droppato:number,
        testo:string,
        tipo_risposta:tipo_risposta,
        stato_completamento:boolean){
            
        }
}

export enum tipo_risposta {
    INDOVINELLO="INDOVINELLO",
    MULTIPLA="MULTIPLA"
}

export enum tipo_scenario {
    INIZIALE="INIZIALE",
    NORMALE="NORMALE",
    FINALE="FINALE"
}