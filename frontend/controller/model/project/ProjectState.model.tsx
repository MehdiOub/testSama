import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


export class ProjectStateDto extends BaseDto{

    public code: string;

    public libelle: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = '';
        }

    getClassName() {
        return "Project state";
    }
}
