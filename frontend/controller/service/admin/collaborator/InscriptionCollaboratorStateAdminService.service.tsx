import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {InscriptionCollaboratorStateDto} from '@/controller/model/collaborator/InscriptionCollaboratorState.model';
import {InscriptionCollaboratorStateCriteria} from '@/controller/criteria/collaborator/InscriptionCollaboratorStateCriteria.model';

export class InscriptionCollaboratorStateAdminService extends AbstractService<InscriptionCollaboratorStateDto, InscriptionCollaboratorStateCriteria>{

    constructor() {
        super(ADMIN_URL , 'inscriptionCollaboratorState/');
    }

};
