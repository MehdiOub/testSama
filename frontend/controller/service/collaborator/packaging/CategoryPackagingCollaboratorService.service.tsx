import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CategoryPackagingDto} from '@/controller/model/packaging/CategoryPackaging.model';
import {CategoryPackagingCriteria} from '@/controller/criteria/packaging/CategoryPackagingCriteria.model';

export class CategoryPackagingCollaboratorService extends AbstractService<CategoryPackagingDto, CategoryPackagingCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'categoryPackaging/');
    }

};
