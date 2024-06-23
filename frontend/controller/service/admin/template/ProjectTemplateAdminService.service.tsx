import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {ProjectTemplateDto} from '@/controller/model/template/ProjectTemplate.model';
import {ProjectTemplateCriteria} from '@/controller/criteria/template/ProjectTemplateCriteria.model';

export class ProjectTemplateAdminService extends AbstractService<ProjectTemplateDto, ProjectTemplateCriteria>{

    constructor() {
        super(ADMIN_URL , 'projectTemplate/');
    }

};
