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

import  {TechnologyDto}  from '@/controller/model/template/Technology.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type TechnologyViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: TechnologyDto,
    t: TFunction
}

const View: React.FC<TechnologyViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<TechnologyDto>({selectedItem, onClose})

    const handleDownload  = () => {
        service.handleDownload();
    }
        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("technology.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("technology.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="code">{t("technology.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="libelle">{t("technology.libelle")}</label>
                <InputText id="libelle" value={selectedItem?.libelle} disabled   />
            </div>

        <div className="field col-6">
            <label htmlFor="logo">{t("technology.logo")}</label>
            <Button label="Download" onClick={handleDownload} />
        </div>

        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
