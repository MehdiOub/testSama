import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ProjectDto} from '@/controller/model/project/Project.model';
import {ProjectCriteria} from '@/controller/criteria/project/ProjectCriteria.model';

export class ProjectCollaboratorService extends AbstractService<ProjectDto, ProjectCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'project/');
    }

};
