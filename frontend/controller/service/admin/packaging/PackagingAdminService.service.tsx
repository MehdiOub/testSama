import { ADMIN_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PackagingDto} from '@/controller/model/packaging/Packaging.model';
import {PackagingCriteria} from '@/controller/criteria/packaging/PackagingCriteria.model';

export class PackagingAdminService extends AbstractService<PackagingDto, PackagingCriteria>{

    constructor() {
        super(ADMIN_URL , 'packaging/');
    }

};
