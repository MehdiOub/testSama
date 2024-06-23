import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {Dialog} from 'primereact/dialog';
import {InputNumber, InputNumberChangeEvent} from 'primereact/inputnumber';
import {InputText} from 'primereact/inputtext';
import {classNames} from 'primereact/utils';
import { InputTextarea } from 'primereact/inputtextarea';
import React, {useEffect, useState} from 'react';
import { Calendar } from 'primereact/calendar';
import { format } from 'date-fns';
import {InputSwitch, InputSwitchChangeEvent} from 'primereact/inputswitch';
import {Dropdown, DropdownChangeEvent} from 'primereact/dropdown';
import {MultiSelect, MultiSelectChangeEvent} from 'primereact/multiselect';
import {MessageService} from '@/utils/zynerator/service/MessageService';

import {TemplateAdminService} from '@/controller/service/admin/template/TemplateAdminService.service';
import  {TemplateDto}  from '@/controller/model/template/Template.model';
import {TemplateCriteria} from "@/controller/criteria/template/TemplateCriteria.model";

import {TechnologyDto} from '@/controller/model/template/Technology.model';
import {TechnologyAdminService} from '@/controller/service/admin/template/TechnologyAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type TemplateCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: TemplateDto) => void,
    showToast: React.Ref<Toast>,
    list: TemplateDto[],
    service: TemplateAdminService,
    t: TFunction
}
const Create: React.FC<TemplateCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.code == '')
                    errorMessages.push("code is required")
                if(item.libelle == '')
                    errorMessages.push("libelle is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new TemplateDto();
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
            onTabChange,
            onDropdownChange,
            hideDialog,
            saveItem,
            formateDate
        } = useCreateHook<TemplateDto, TemplateCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [technologys, setTechnologys] = useState<TechnologyDto[]>([]);


    const technologyAdminService = new TechnologyAdminService();
    useEffect(() => {
        technologyAdminService.getList().then(({data}) => setTechnologys(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("template.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("template.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("template.code")}</label>
                        <InputText id="code" value={item.code} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="libelle">{t("template.libelle")}</label>
                        <InputText id="libelle" value={item.libelle} onChange={(e) => onInputTextChange(e, 'libelle')} required className={classNames({'p-invalid': submitted && !item.libelle})} />
                        {submitted && !item.libelle && <small className="p-invalid">Libelle is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("template.description")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="description" value={item.description} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="addingDate">{t("template.addingDate")}</label>
                        <Calendar id="addingDate" value={item.addingDate} onChange={(e) => onInputDateChange(e, 'addingDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="lastUpdateDate">{t("template.lastUpdateDate")}</label>
                        <Calendar id="lastUpdateDate" value={item.lastUpdateDate} onChange={(e) => onInputDateChange(e, 'lastUpdateDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="templateTags">{t("template.templateTags")}</label>
                        <InputText id="templateTags" value={item.templateTags} onChange={(e) => onInputTextChange(e, 'templateTags')} required className={classNames({'p-invalid': submitted && !item.templateTags})} />
                        {submitted && !item.templateTags && <small className="p-invalid">TemplateTags is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="price">{t("template.price")}</label>
                        <InputNumber id="price" value={item.price} onChange={(e) => onInputNumerChange(e, 'price')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="technology">{t("template.technology")}</label>
                        <Dropdown  id="technologyDropdown"  value={item.technology} options={technologys} onChange={(e) => onDropdownChange(e, 'technology')}   placeholder={t("template.technologyPlaceHolder")} filter filterPlaceholder={t("template.technologyPlaceHolderFilter")} optionLabel="libelle" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
