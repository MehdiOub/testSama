import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {InscriptionCollaboratorDto} from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorCriteria} from '@/controller/criteria/collaborator/InscriptionCollaboratorCriteria.model';

export class InscriptionCollaboratorCollaboratorService extends AbstractService<InscriptionCollaboratorDto, InscriptionCollaboratorCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'inscriptionCollaborator/');
    }

};
