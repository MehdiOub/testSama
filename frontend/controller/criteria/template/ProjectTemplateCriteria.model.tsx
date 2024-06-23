import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {ProjectCriteria} from '@/controller/criteria/project/ProjectCriteria.model';
import {TemplateCriteria} from '@/controller/criteria/template/TemplateCriteria.model';

export class ProjectTemplateCriteria  extends  BaseCriteria {

    public id: number | null;;

    public template: TemplateCriteria ;
    public templates: Array<TemplateCriteria> ;
    public project: ProjectCriteria ;
    public projects: Array<ProjectCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.template = new TemplateCriteria();
        this.templates = new Array<TemplateCriteria>() ;
        this.project = new ProjectCriteria();
        this.projects = new Array<ProjectCriteria>() ;
    }

}
