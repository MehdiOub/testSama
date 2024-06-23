import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {TemplateDto} from '@/controller/model/template/Template.model';
import {TemplateCriteria} from '@/controller/criteria/template/TemplateCriteria.model';

export class TemplateAdminService extends AbstractService<TemplateDto, TemplateCriteria>{

    constructor() {
        super(ADMIN_URL , 'template/');
    }

};
