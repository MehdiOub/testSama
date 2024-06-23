import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


export class InscriptionCollaboratorStateDto extends BaseDto{

    public code: string;

    public libelle: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = '';
        }

    getClassName() {
        return "Inscription collaborator state";
    }
}
