import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ProjectTemplateDto} from '@/controller/model/template/ProjectTemplate.model';
import {ProjectTemplateCriteria} from '@/controller/criteria/template/ProjectTemplateCriteria.model';

export class ProjectTemplateCollaboratorService extends AbstractService<ProjectTemplateDto, ProjectTemplateCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'projectTemplate/');
    }

};
