import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {CollaboratorDto} from '@/controller/model/collaborator/Collaborator.model';
import {PackagingDto} from '@/controller/model/packaging/Packaging.model';
import {InscriptionCollaboratorStateDto} from '@/controller/model/collaborator/InscriptionCollaboratorState.model';

export class InscriptionCollaboratorDto extends BaseDto{

    public reference: string;

   public startDate: null | Date;

   public endDate: null | Date;

   public renewDate: null | Date;

    public consumedEntity: null | number;

    public consumedProjet: null | number;

    public consumedAttribut: null | number;

    public consumedIndicator: null | number;

    public packaging: null | PackagingDto ;
    public collaborator: null | CollaboratorDto ;
    public inscriptionCollaboratorState: null | InscriptionCollaboratorStateDto ;


    constructor() {
        super();
        this.reference = '';
        this.startDate = null;
        this.endDate = null;
        this.renewDate = null;
        this.consumedEntity = null;
        this.consumedProjet = null;
        this.consumedAttribut = null;
        this.consumedIndicator = null;
        this.packaging = null;
        this.collaborator = null;
        this.inscriptionCollaboratorState = null;
        }

    getClassName() {
        return "Inscription collaborator";
    }
}
