import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaimentCollaboratorDto} from '@/controller/model/collaborator/PaimentCollaborator.model';
import {PaimentCollaboratorCriteria} from '@/controller/criteria/collaborator/PaimentCollaboratorCriteria.model';

export class PaimentCollaboratorCollaboratorService extends AbstractService<PaimentCollaboratorDto, PaimentCollaboratorCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'paimentCollaborator/');
    }

};
