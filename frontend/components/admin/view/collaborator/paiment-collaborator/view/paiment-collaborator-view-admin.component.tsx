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

import  {PaimentCollaboratorDto}  from '@/controller/model/collaborator/PaimentCollaborator.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type PaimentCollaboratorViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: PaimentCollaboratorDto,
    t: TFunction
}

const View: React.FC<PaimentCollaboratorViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<PaimentCollaboratorDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("paimentCollaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("paimentCollaborator.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="libelle">{t("paimentCollaborator.libelle")}</label>
                <InputText id="libelle" value={selectedItem?.libelle} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("paimentCollaborator.description")}</label>
                <span className="p-float-label">
                   <InputTextarea id="description" value={selectedItem?.description} disabled rows={5} cols={30} />
                </span>
            </div>

            <div className="field col-6">
                <label htmlFor="code">{t("paimentCollaborator.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="amountToPaid">{t("paimentCollaborator.amountToPaid")}</label>
                    <InputNumber id="amountToPaid" value={selectedItem.amountToPaid} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="total">{t("paimentCollaborator.total")}</label>
                    <InputNumber id="total" value={selectedItem.total} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="discount">{t("paimentCollaborator.discount")}</label>
                    <InputNumber id="discount" value={selectedItem.discount} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="remaining">{t("paimentCollaborator.remaining")}</label>
                    <InputNumber id="remaining" value={selectedItem.remaining} disabled/>
                </div>

        <div className="field col-6">
            <label htmlFor="paiementDate">{t("paimentCollaborator.paiementDate")}</label>
            <Calendar id="paiementDate" value={adaptDate(selectedItem?.paiementDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="inscriptionCollaborator">{t("paimentCollaborator.inscriptionCollaborator")}</label>
                    <InputText  id="inscriptionCollaboratorDropdown"  value={selectedItem?.inscriptionCollaborator?.reference}  disabled  />
                </div>
                <div className="field col-6">
                    <label htmlFor="paimentCollaboratorState">{t("paimentCollaborator.paimentCollaboratorState")}</label>
                    <InputText  id="paimentCollaboratorStateDropdown"  value={selectedItem?.paimentCollaboratorState?.libelle}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
