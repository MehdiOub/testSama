import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ProjectDto} from '@/controller/model/project/Project.model';
import {ProjectCriteria} from '@/controller/criteria/project/ProjectCriteria.model';

export class ProjectAdminService extends AbstractService<ProjectDto, ProjectCriteria>{

    constructor() {
        super(ADMIN_URL , 'project/');
    }

};
