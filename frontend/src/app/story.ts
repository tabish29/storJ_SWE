export class story {
    constructor(
        public id: number,
        public id_creatore: number,
        public titolo: string,
        public categoria: string,
        public numero_scenari: number,
        public statoCompletamento: boolean
    ) {

    }
}