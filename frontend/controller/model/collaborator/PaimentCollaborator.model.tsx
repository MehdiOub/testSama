import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {PaimentCollaboratorStateDto} from '@/controller/model/collaborator/PaimentCollaboratorState.model';
import {InscriptionCollaboratorDto} from '@/controller/model/collaborator/InscriptionCollaborator.model';

export class PaimentCollaboratorDto extends BaseDto{

    public libelle: string;

    public description: string;

    public code: string;

    public amountToPaid: null | number;

    public total: null | number;

    public discount: null | number;

    public remaining: null | number;

   public paiementDate: null | Date;

    public inscriptionCollaborator: null | InscriptionCollaboratorDto ;
    public paimentCollaboratorState: null | PaimentCollaboratorStateDto ;


    constructor() {
        super();
        this.libelle = '';
        this.description = '';
        this.code = '';
        this.amountToPaid = null;
        this.total = null;
        this.discount = null;
        this.remaining = null;
        this.paiementDate = null;
        this.inscriptionCollaborator = null;
        this.paimentCollaboratorState = null;
        }

    getClassName() {
        return "Paiment collaborator";
    }
}
