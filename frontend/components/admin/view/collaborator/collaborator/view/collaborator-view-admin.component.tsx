import {Column} from 'primereact/column';
import {TabPanel, TabView} from 'primereact/tabview';
import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {InputNumber} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {InputTextarea} from 'primereact/inputtextarea';
import React from 'react';
import {Calendar} from 'primereact/calendar';
import {InputSwitch} from 'primereact/inputswitch';
import {TFunction} from "i18next";

import {RoleDto} from "@/utils/zynerator/dto/RoleDto.model";
import  {CollaboratorDto}  from '@/controller/model/collaborator/Collaborator.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type CollaboratorViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: CollaboratorDto,
    t: TFunction
}

const View: React.FC<CollaboratorViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<CollaboratorDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("collaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("collaborator.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="description">{t("collaborator.description")}</label>
                <InputText id="description" value={selectedItem?.description} disabled   />
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="credentialsNonExpired">{t("collaborator.credentialsNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="credentialsNonExpired" checked={selectedItem?.credentialsNonExpired} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="enabled">{t("collaborator.enabled")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="enabled" checked={selectedItem?.enabled} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonExpired">{t("collaborator.accountNonExpired")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonExpired" checked={selectedItem?.accountNonExpired} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="accountNonLocked">{t("collaborator.accountNonLocked")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="accountNonLocked" checked={selectedItem?.accountNonLocked} disabled />
                    </span>
            </div>
            </div>

        <div className="field col-6">
            <div  className="label-inputswitch">
                    <label htmlFor="passwordChanged">{t("collaborator.passwordChanged")}</label>
                    <span className="p-float-label">
                        <InputSwitch  id="passwordChanged" checked={selectedItem?.passwordChanged} disabled />
                    </span>
            </div>
            </div>

            <div className="field col-6">
                <label htmlFor="username">{t("collaborator.username")}</label>
                <InputText id="username" value={selectedItem?.username} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="password">{t("collaborator.password")}</label>
                    <InputNumber id="password" value={selectedItem.password} disabled/>
                </div>

        <div className="field col-6">
            <label htmlFor="roles">Roles</label>
            <InputText id="roles" value={selectedItem?.roles?.map(e=>e.label).join(",")} disabled   />
        </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
