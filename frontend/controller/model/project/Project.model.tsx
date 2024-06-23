import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {ProjectStateDto} from '@/controller/model/project/ProjectState.model';
import {TemplateDto} from '@/controller/model/template/Template.model';
import {ProjectTemplateDto} from '@/controller/model/template/ProjectTemplate.model';

export class ProjectDto extends BaseDto{

    public code: string;


   public generatedDate: null | Date;

    public yaml: string;

    public projectState: null | ProjectStateDto ;
     public projectTemplates: Array<ProjectTemplateDto>;


    constructor() {
        super();
        this.code = '';
        this.generatedDate = null;
        this.yaml = '';
        this.projectState = null;
        this.projectTemplates = new Array<ProjectTemplateDto>();
        }

    getClassName() {
        return "Project";
    }
}
