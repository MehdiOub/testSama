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

import  {PackagingDto}  from '@/controller/model/packaging/Packaging.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type PackagingViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: PackagingDto,
    t: TFunction
}

const View: React.FC<PackagingViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<PackagingDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("packaging.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("packaging.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="name">{t("packaging.name")}</label>
                <InputText id="name" value={selectedItem?.name} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="code">{t("packaging.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

            <div className="field col-6">
                <label htmlFor="description">{t("packaging.description")}</label>
                <span className="p-float-label">
                   <InputTextarea id="description" value={selectedItem?.description} disabled rows={5} cols={30} />
                </span>
            </div>

        <div className="field col-6">
            <label htmlFor="dateStart">{t("packaging.dateStart")}</label>
            <Calendar id="dateStart" value={adaptDate(selectedItem?.dateStart)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

        <div className="field col-6">
            <label htmlFor="dateEnd">{t("packaging.dateEnd")}</label>
            <Calendar id="dateEnd" value={adaptDate(selectedItem?.dateEnd)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

                <div className="field col-6">
                    <label htmlFor="price">{t("packaging.price")}</label>
                    <InputNumber id="price" value={selectedItem.price} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="maxEntity">{t("packaging.maxEntity")}</label>
                    <InputNumber id="maxEntity" value={selectedItem.maxEntity} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="maxProjet">{t("packaging.maxProjet")}</label>
                    <InputNumber id="maxProjet" value={selectedItem.maxProjet} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="maxAttribut">{t("packaging.maxAttribut")}</label>
                    <InputNumber id="maxAttribut" value={selectedItem.maxAttribut} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="maxIndicator">{t("packaging.maxIndicator")}</label>
                    <InputNumber id="maxIndicator" value={selectedItem.maxIndicator} disabled/>
                </div>

                <div className="field col-6">
                    <label htmlFor="categoryPackaging">{t("packaging.categoryPackaging")}</label>
                    <InputText  id="categoryPackagingDropdown"  value={selectedItem?.categoryPackaging?.libelle}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
