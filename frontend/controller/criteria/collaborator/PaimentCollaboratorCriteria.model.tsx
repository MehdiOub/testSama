import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {PaimentCollaboratorStateCriteria} from '@/controller/criteria/collaborator/PaimentCollaboratorStateCriteria.model';
import {InscriptionCollaboratorCriteria} from '@/controller/criteria/collaborator/InscriptionCollaboratorCriteria.model';

export class PaimentCollaboratorCriteria  extends  BaseCriteria {

    public id: number | null;;

    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public code: string;
    public codeLike: string;
     public amountToPaid: null | number;
     public amountToPaidMin: null | number;
     public amountToPaidMax: null | number;
     public total: null | number;
     public totalMin: null | number;
     public totalMax: null | number;
     public discount: null | number;
     public discountMin: null | number;
     public discountMax: null | number;
     public remaining: null | number;
     public remainingMin: null | number;
     public remainingMax: null | number;
    public paiementDate: null | Date;
    public paiementDateFrom: null | Date;
    public paiementDateTo: null | Date;
    public inscriptionCollaborator: InscriptionCollaboratorCriteria ;
    public inscriptionCollaborators: Array<InscriptionCollaboratorCriteria> ;
    public paimentCollaboratorState: PaimentCollaboratorStateCriteria ;
    public paimentCollaboratorStates: Array<PaimentCollaboratorStateCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.code = '';
        this.codeLike = '';
        this.amountToPaid = null;
        this.amountToPaidMin = null;
        this.amountToPaidMax = null;
        this.total = null;
        this.totalMin = null;
        this.totalMax = null;
        this.discount = null;
        this.discountMin = null;
        this.discountMax = null;
        this.remaining = null;
        this.remainingMin = null;
        this.remainingMax = null;
        this.paiementDate = null;
        this.paiementDateFrom  = null;
        this.paiementDateTo = null;
        this.inscriptionCollaborator = new InscriptionCollaboratorCriteria();
        this.inscriptionCollaborators = new Array<InscriptionCollaboratorCriteria>() ;
        this.paimentCollaboratorState = new PaimentCollaboratorStateCriteria();
        this.paimentCollaboratorStates = new Array<PaimentCollaboratorStateCriteria>() ;
    }

}
