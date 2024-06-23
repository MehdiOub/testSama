import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import {Calendar} from 'primereact/calendar';
import { format } from 'date-fns';
import { parse } from 'date-fns';
import { InputSwitch } from 'primereact/inputswitch';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';


import {MessageService} from '@/utils/zynerator/service/MessageService';


import {TFunction} from "i18next";
import {Toast} from "primereact/toast";

import useEditHook from "@/utils/zyhook/useEdit.hook";


import {PackagingAdminService} from '@/controller/service/admin/packaging/PackagingAdminService.service';
import  {PackagingDto}  from '@/controller/model/packaging/Packaging.model';
import {PackagingCriteria} from "@/controller/criteria/packaging/PackagingCriteria.model";


import {CategoryPackagingDto} from '@/controller/model/packaging/CategoryPackaging.model';
import {CategoryPackagingAdminService} from '@/controller/service/admin/packaging/CategoryPackagingAdminService.service';


type PackagingEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: PackagingDto
    update: (item: PackagingDto) => void,
    list: PackagingDto[],
    service: PackagingAdminService,
    t: TFunction
}
const Edit: React.FC<PackagingEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        if(item.code == '')
            errorMessages.push("code is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new PackagingDto();


    const {
        item,
        setItem,
        submitted,
        setSubmitted,
        activeIndex,
        setActiveIndex,
        activeTab,
        setActiveTab,
        onInputTextChange,
        onInputDateChange,
        onInputNumerChange,
        onMultiSelectChange,
        onBooleanInputChange,
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<PackagingDto, PackagingCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [categoryPackagings, setCategoryPackagings] = useState<CategoryPackagingDto[]>([]);


    const categoryPackagingAdminService = new CategoryPackagingAdminService();
    useEffect(() => {
    categoryPackagingAdminService.getList().then(({data}) => setCategoryPackagings(data)).catch(error => console.log(error));


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("packaging.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("packaging.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="name">{t("packaging.name")}</label>
                        <InputText id="name" value={item ? item.name : ''} onChange={(e) => onInputTextChange(e, 'name')} required className={classNames({'p-invalid': submitted && !item.name})} />
                        {submitted && !item.name && <small className="p-invalid">Name is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="code">{t("packaging.code")}</label>
                        <InputText id="code" value={item ? item.code : ''} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("packaging.description")}</label>
                        <span className="p-float-label">
                            <InputTextarea id="description" value={item ? item.description : ''} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                        </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="dateStart">{t("packaging.dateStart")}</label>
                        <Calendar id="dateStart" value={adaptDate(item?.dateStart)} onChange={(e) => onInputDateChange(e, 'dateStart')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="dateEnd">{t("packaging.dateEnd")}</label>
                        <Calendar id="dateEnd" value={adaptDate(item?.dateEnd)} onChange={(e) => onInputDateChange(e, 'dateEnd')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="price">{t("packaging.price")}</label>
                        <InputNumber id="price" value={item ? item.price : 0} onChange={(e) => onInputNumerChange(e, 'price')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="maxEntity">{t("packaging.maxEntity")}</label>
                        <InputNumber id="maxEntity" value={item ? item.maxEntity : 0} onChange={(e) => onInputNumerChange(e, 'maxEntity')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="maxProjet">{t("packaging.maxProjet")}</label>
                        <InputNumber id="maxProjet" value={item ? item.maxProjet : 0} onChange={(e) => onInputNumerChange(e, 'maxProjet')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="maxAttribut">{t("packaging.maxAttribut")}</label>
                        <InputNumber id="maxAttribut" value={item ? item.maxAttribut : 0} onChange={(e) => onInputNumerChange(e, 'maxAttribut')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="maxIndicator">{t("packaging.maxIndicator")}</label>
                        <InputNumber id="maxIndicator" value={item ? item.maxIndicator : 0} onChange={(e) => onInputNumerChange(e, 'maxIndicator')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="categoryPackaging">{t("packaging.categoryPackaging")}</label>
                        <Dropdown  id="categoryPackagingDropdown"  value={item ? item.categoryPackaging : ''} options={categoryPackagings} onChange={(e) => onDropdownChange(e, 'categoryPackaging')}   placeholder="Sélectionnez un categoryPackaging" filter filterPlaceholder="Rechercher un categoryPackaging" optionLabel="libelle" showClear />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


