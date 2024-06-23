import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {ProjectStateCriteria} from '@/controller/criteria/project/ProjectStateCriteria.model';

export class ProjectCriteria  extends  BaseCriteria {

    public id: number | null;;

    public code: string;
    public codeLike: string;
    public generatedDate: null | Date;
    public generatedDateFrom: null | Date;
    public generatedDateTo: null | Date;
    public yaml: string;
    public yamlLike: string;
    public projectState: ProjectStateCriteria ;
    public projectStates: Array<ProjectStateCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.code = '';
        this.codeLike = '';
        this.generatedDate = null;
        this.generatedDateFrom  = null;
        this.generatedDateTo = null;
        this.yaml = '';
        this.yamlLike = '';
        this.projectState = new ProjectStateCriteria();
        this.projectStates = new Array<ProjectStateCriteria>() ;
    }

}
