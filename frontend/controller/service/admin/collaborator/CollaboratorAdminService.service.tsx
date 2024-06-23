import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CollaboratorDto} from '@/controller/model/collaborator/Collaborator.model';
import {CollaboratorCriteria} from '@/controller/criteria/collaborator/CollaboratorCriteria.model';

export class CollaboratorAdminService extends AbstractService<CollaboratorDto, CollaboratorCriteria>{

    constructor() {
        super(ADMIN_URL , 'collaborator/');
    }

};
