import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {CollaboratorCriteria} from '@/controller/criteria/collaborator/CollaboratorCriteria.model';
import {PackagingCriteria} from '@/controller/criteria/packaging/PackagingCriteria.model';
import {InscriptionCollaboratorStateCriteria} from '@/controller/criteria/collaborator/InscriptionCollaboratorStateCriteria.model';

export class InscriptionCollaboratorCriteria  extends  BaseCriteria {

    public id: number | null;;

    public reference: string;
    public referenceLike: string;
    public startDate: null | Date;
    public startDateFrom: null | Date;
    public startDateTo: null | Date;
    public endDate: null | Date;
    public endDateFrom: null | Date;
    public endDateTo: null | Date;
    public renewDate: null | Date;
    public renewDateFrom: null | Date;
    public renewDateTo: null | Date;
     public consumedEntity: null | number;
     public consumedEntityMin: null | number;
     public consumedEntityMax: null | number;
     public consumedProjet: null | number;
     public consumedProjetMin: null | number;
     public consumedProjetMax: null | number;
     public consumedAttribut: null | number;
     public consumedAttributMin: null | number;
     public consumedAttributMax: null | number;
     public consumedIndicator: null | number;
     public consumedIndicatorMin: null | number;
     public consumedIndicatorMax: null | number;
    public packaging: PackagingCriteria ;
    public packagings: Array<PackagingCriteria> ;
    public collaborator: CollaboratorCriteria ;
    public collaborators: Array<CollaboratorCriteria> ;
    public inscriptionCollaboratorState: InscriptionCollaboratorStateCriteria ;
    public inscriptionCollaboratorStates: Array<InscriptionCollaboratorStateCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.reference = '';
        this.referenceLike = '';
        this.startDate = null;
        this.startDateFrom  = null;
        this.startDateTo = null;
        this.endDate = null;
        this.endDateFrom  = null;
        this.endDateTo = null;
        this.renewDate = null;
        this.renewDateFrom  = null;
        this.renewDateTo = null;
        this.consumedEntity = null;
        this.consumedEntityMin = null;
        this.consumedEntityMax = null;
        this.consumedProjet = null;
        this.consumedProjetMin = null;
        this.consumedProjetMax = null;
        this.consumedAttribut = null;
        this.consumedAttributMin = null;
        this.consumedAttributMax = null;
        this.consumedIndicator = null;
        this.consumedIndicatorMin = null;
        this.consumedIndicatorMax = null;
        this.packaging = new PackagingCriteria();
        this.packagings = new Array<PackagingCriteria>() ;
        this.collaborator = new CollaboratorCriteria();
        this.collaborators = new Array<CollaboratorCriteria>() ;
        this.inscriptionCollaboratorState = new InscriptionCollaboratorStateCriteria();
        this.inscriptionCollaboratorStates = new Array<InscriptionCollaboratorStateCriteria>() ;
    }

}
