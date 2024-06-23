import {BaseCriteria} from "@/utils/zynerator/criteria/BaseCriteria.model";

import {TechnologyCriteria} from '@/controller/criteria/template/TechnologyCriteria.model';

export class TemplateCriteria  extends  BaseCriteria {

    public id: number | null;;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public addingDate: null | Date;
    public addingDateFrom: null | Date;
    public addingDateTo: null | Date;
    public lastUpdateDate: null | Date;
    public lastUpdateDateFrom: null | Date;
    public lastUpdateDateTo: null | Date;
    public templateTags: string;
    public templateTagsLike: string;
     public price: null | number;
     public priceMin: null | number;
     public priceMax: null | number;
    public technology: TechnologyCriteria ;
    public technologys: Array<TechnologyCriteria> ;


    constructor() {
        super();
        this.id=null;
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.addingDate = null;
        this.addingDateFrom  = null;
        this.addingDateTo = null;
        this.lastUpdateDate = null;
        this.lastUpdateDateFrom  = null;
        this.lastUpdateDateTo = null;
        this.templateTags = '';
        this.templateTagsLike = '';
        this.price = null;
        this.priceMin = null;
        this.priceMax = null;
        this.technology = new TechnologyCriteria();
        this.technologys = new Array<TechnologyCriteria>() ;
    }

}
