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

import  {ProjectDto}  from '@/controller/model/project/Project.model';

import useViewHook from "@/utils/zyhook/useViewhook";

type ProjectViewAdminType = {
    visible: boolean,
    onClose: () => void,
    selectedItem: ProjectDto,
    t: TFunction
}

const View: React.FC<ProjectViewAdminType> = ({visible,onClose,selectedItem, t}) => {

    const {
    onTabChange,
    hideDialog,
    itemDialogFooter,
    formateDate,
    parse,
    parseToIsoFormat,
    adaptDate,
    activeIndex
    } = useViewHook<ProjectDto>({selectedItem, onClose})

        return(
<Dialog visible={visible} style={{width: '70vw'}} header={t("project.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
<TabView activeIndex={activeIndex} onTabChange={onTabChange}>
<TabPanel header={t("project.tabPan")}>
    <div className="formgrid grid">

            <div className="field col-6">
                <label htmlFor="code">{t("project.code")}</label>
                <InputText id="code" value={selectedItem?.code} disabled   />
            </div>

                <div className="field col-6">
                    <label htmlFor="name">{t("project.name")}</label>
                    <InputNumber id="name" value={selectedItem.name} disabled/>
                </div>

        <div className="field col-6">
            <label htmlFor="generatedDate">{t("project.generatedDate")}</label>
            <Calendar id="generatedDate" value={adaptDate(selectedItem?.generatedDate)} disabled dateFormat="dd/mm/yy" showIcon={true}  />
        </div>

            <div className="field col-6">
                <label htmlFor="yaml">{t("project.yaml")}</label>
                <span className="p-float-label">
                   <InputTextarea id="yaml" value={selectedItem?.yaml} disabled rows={5} cols={30} />
                </span>
            </div>

                <div className="field col-6">
                    <label htmlFor="projectState">{t("project.projectState")}</label>
                    <InputText  id="projectStateDropdown"  value={selectedItem?.projectState?.libelle}  disabled  />
                </div>
        </div>
</TabPanel>
</TabView>
</Dialog>
);
};
export default View;
