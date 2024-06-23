import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {ProjectDto} from '@/controller/model/project/Project.model';
import {TemplateDto} from '@/controller/model/template/Template.model';

export class ProjectTemplateDto extends BaseDto{

    public template: null | TemplateDto ;
    public project: null | ProjectDto ;


    constructor() {
        super();
        this.template = null;
        this.project = null;
        }

    getClassName() {
        return "Project template";
    }
}
