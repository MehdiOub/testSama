import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";

import {CategoryPackagingDto} from '@/controller/model/packaging/CategoryPackaging.model';

export class PackagingDto extends BaseDto{

    public name: string;

    public code: string;

    public description: string;

   public dateStart: null | Date;

   public dateEnd: null | Date;

    public price: null | number;

    public maxEntity: null | number;

    public maxProjet: null | number;

    public maxAttribut: null | number;

    public maxIndicator: null | number;

    public categoryPackaging: null | CategoryPackagingDto ;


    constructor() {
        super();
        this.name = '';
        this.code = '';
        this.description = '';
        this.dateStart = null;
        this.dateEnd = null;
        this.price = null;
        this.maxEntity = null;
        this.maxProjet = null;
        this.maxAttribut = null;
        this.maxIndicator = null;
        this.categoryPackaging = null;
        }

    getClassName() {
        return "Packaging";
    }
}
