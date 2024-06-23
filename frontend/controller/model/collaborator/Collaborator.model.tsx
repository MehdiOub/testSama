import {BaseDto} from "@/utils/zynerator/dto/BaseDto.model";


import {RoleDto} from "app/zynerator/dto/RoleDto.model";
export class CollaboratorDto extends BaseDto{

    public description: string;

   public credentialsNonExpired: boolean;

   public enabled: boolean;

   public accountNonExpired: boolean;

   public accountNonLocked: boolean;

   public passwordChanged: boolean;

    public username: string;

    public password: string;


    public roles: RoleDto[];

    constructor() {
        super();
        this.description = '';
        this.credentialsNonExpired = null;
        this.enabled = null;
        this.accountNonExpired = null;
        this.accountNonLocked = null;
        this.passwordChanged = null;
        this.username = '';
        this.password = '';
        }

    getClassName() {
        return "Collaborator";
    }
}
