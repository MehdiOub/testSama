import { COLLABORATOR_URL } from '@/layout/AppConfig';
import AbstractService from "@/utils/zynerator/service/AbstractService";

import {PackagingDto} from '@/controller/model/packaging/Packaging.model';
import {PackagingCriteria} from '@/controller/criteria/packaging/PackagingCriteria.model';

export class PackagingCollaboratorService extends AbstractService<PackagingDto, PackagingCriteria>{

    constructor() {
        super(COLLABORATOR_URL , 'packaging/');
    }

};
