import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CollaboratorDto} from '@/controller/model/collaborator/Collaborator.model';
import {CollaboratorCriteria} from '@/controller/criteria/collaborator/CollaboratorCriteria.model';

export class CollaboratorCollaboratorService extends AbstractService<CollaboratorDto, CollaboratorCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'collaborator/');
    }

};
