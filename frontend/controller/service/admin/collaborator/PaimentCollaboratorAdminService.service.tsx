import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PaimentCollaboratorDto} from '@/controller/model/collaborator/PaimentCollaborator.model';
import {PaimentCollaboratorCriteria} from '@/controller/criteria/collaborator/PaimentCollaboratorCriteria.model';

export class PaimentCollaboratorAdminService extends AbstractService<PaimentCollaboratorDto, PaimentCollaboratorCriteria>{

    constructor() {
        super(ADMIN_URL , 'paimentCollaborator/');
    }

};
