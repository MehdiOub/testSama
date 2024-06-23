import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ProjectStateDto} from '@/controller/model/project/ProjectState.model';
import {ProjectStateCriteria} from '@/controller/criteria/project/ProjectStateCriteria.model';

export class ProjectStateCollaboratorService extends AbstractService<ProjectStateDto, ProjectStateCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'projectState/');
    }

};
