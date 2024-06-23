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

import  {InscriptionCollaboratorDto}  from '@/controller/model/collaborator/InscriptionCollaborator.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type InscriptionCollaboratorViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: InscriptionCollaboratorDto,
    t: TFunction
}

const View: React.FC<InscriptionCollaboratorViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<InscriptionCollaboratorDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("inscriptionCollaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("inscriptionCollaborator.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="reference">{t("inscriptionCollaborator.reference")}</label>
                <InputText id="reference" value={selectedItem?.reference} disabled   />
            </div>

        <div className="field col-6">
            <label htmlFor="startDate">{t("inscriptionCollaborator.startDate")}</label>
            <Calendar id="startDate" value={adaptDate(selectedItem?.startDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        <div className="field col-6">
            <label htmlFor="endDate">{t("inscriptionCollaborator.endDate")}</label>
            <Calendar id="endDate" value={adaptDate(selectedItem?.endDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        <div className="field col-6">
            <label htmlFor="renewDate">{t("inscriptionCollaborator.renewDate")}</label>
            <Calendar id="renewDate" value={adaptDate(selectedItem?.renewDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="packaging">{t("inscriptionCollaborator.packaging")}</label>
                    <InputText  id="packagingDropdown"  value={selectedItem?.packaging?.code}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="consumedEntity">{t("inscriptionCollaborator.consumedEntity")}</label>
                    <InputNumber id="consumedEntity" value={selectedItem.consumedEntity} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="consumedProjet">{t("inscriptionCollaborator.consumedProjet")}</label>
                    <InputNumber id="consumedProjet" value={selectedItem.consumedProjet} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="consumedAttribut">{t("inscriptionCollaborator.consumedAttribut")}</label>
                    <InputNumber id="consumedAttribut" value={selectedItem.consumedAttribut} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="consumedIndicator">{t("inscriptionCollaborator.consumedIndicator")}</label>
                    <InputNumber id="consumedIndicator" value={selectedItem.consumedIndicator} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="collaborator">{t("inscriptionCollaborator.collaborator")}</label>
                    <InputText  id="collaboratorDropdown"  value={selectedItem?.collaborator?.id}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="inscriptionCollaboratorState">{t("inscriptionCollaborator.inscriptionCollaboratorState")}</label>
                    <InputText  id="inscriptionCollaboratorStateDropdown"  value={selectedItem?.inscriptionCollaboratorState?.libelle}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
