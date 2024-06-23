import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaimentCollaboratorStateDto} from '@/controller/model/collaborator/PaimentCollaboratorState.model';
import {PaimentCollaboratorStateCriteria} from '@/controller/criteria/collaborator/PaimentCollaboratorStateCriteria.model';

export class PaimentCollaboratorStateCollaboratorService extends AbstractService<PaimentCollaboratorStateDto, PaimentCollaboratorStateCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'paimentCollaboratorState/');
    }

};
