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

import  {TemplateDto}  from '@/controller/model/template/Template.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type TemplateViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: TemplateDto,
    t: TFunction
}

const View: React.FC<TemplateViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<TemplateDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("template.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("template.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="code">{t("template.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="libelle">{t("template.libelle")}</label>
                <InputText id="libelle" value={selectedItem?.libelle} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("template.description")}</label>
                <span className="p-float-label">
                   <InputTextarea id="description" value={selectedItem?.description} disabled rows={5} cols={30} />
                </span>
            </div>

        <div className="field col-6">
            <label htmlFor="addingDate">{t("template.addingDate")}</label>
            <Calendar id="addingDate" value={adaptDate(selectedItem?.addingDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        <div className="field col-6">
            <label htmlFor="lastUpdateDate">{t("template.lastUpdateDate")}</label>
            <Calendar id="lastUpdateDate" value={adaptDate(selectedItem?.lastUpdateDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

            <div className="field col-6">
                <label htmlFor="templateTags">{t("template.templateTags")}</label>
                <InputText id="templateTags" value={selectedItem?.templateTags} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="price">{t("template.price")}</label>
                    <InputNumber id="price" value={selectedItem.price} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="technology">{t("template.technology")}</label>
                    <InputText  id="technologyDropdown"  value={selectedItem?.technology?.libelle}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
