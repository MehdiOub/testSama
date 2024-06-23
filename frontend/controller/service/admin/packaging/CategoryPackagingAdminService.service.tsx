import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {CategoryPackagingDto} from '@/controller/model/packaging/CategoryPackaging.model';
import {CategoryPackagingCriteria} from '@/controller/criteria/packaging/CategoryPackagingCriteria.model';

export class CategoryPackagingAdminService extends AbstractService<CategoryPackagingDto, CategoryPackagingCriteria>{

    constructor() {
        super(ADMIN_URL , 'categoryPackaging/');
    }

};
