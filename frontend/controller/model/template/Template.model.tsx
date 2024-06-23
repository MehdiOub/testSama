import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {TechnologyDto} from '@/controller/model/template/Technology.model';

export class TemplateDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

   public addingDate: null | Date;

   public lastUpdateDate: null | Date;

    public templateTags: string;

    public price: null | number;

    public technology: null | TechnologyDto ;


    constructor() {
        super();
        this.code = '';
        this.libelle = '';
        this.description = '';
        this.addingDate = null;
        this.lastUpdateDate = null;
        this.templateTags = '';
        this.price = null;
        this.technology = null;
        }

    getClassName() {
        return "Template";
    }
}
