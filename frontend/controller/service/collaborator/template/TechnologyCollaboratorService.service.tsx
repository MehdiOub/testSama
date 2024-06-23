import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {TechnologyDto} from '@/controller/model/template/Technology.model';
import {TechnologyCriteria} from '@/controller/criteria/template/TechnologyCriteria.model';

export class TechnologyCollaboratorService extends AbstractService<TechnologyDto, TechnologyCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'technology/');
    }

};
