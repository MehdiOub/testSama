import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";


export class InscriptionCollaboratorStateCriteria  extends  BaseCriteria {

    public id: number | null;;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;


    constructor() {
        super();
        this.id=null;
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
    }

}
