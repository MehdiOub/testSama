import {Button} from 'primereact/button';
import {Column} from 'primereact/column';
import {TabView, TabPanel} from 'primereact/tabview';
import {DataTable} from 'primereact/datatable';
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

import {ProjectAdminService} from '@/controller/service/admin/project/ProjectAdminService.service';
import  {ProjectDto}  from '@/controller/model/project/Project.model';
import {ProjectCriteria} from "@/controller/criteria/project/ProjectCriteria.model";

import {ProjectStateDto} from '@/controller/model/project/ProjectState.model';
import {ProjectStateAdminService} from '@/controller/service/admin/project/ProjectStateAdminService.service';
import {TemplateDto} from '@/controller/model/template/Template.model';
import {TemplateAdminService} from '@/controller/service/admin/template/TemplateAdminService.service';
import {ProjectTemplateDto} from '@/controller/model/template/ProjectTemplate.model';
import {ProjectTemplateAdminService} from '@/controller/service/admin/template/ProjectTemplateAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type ProjectCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: ProjectDto) => void,
    showToast: React.Ref<Toast>,
    list: ProjectDto[],
    service: ProjectAdminService,
    t: TFunction
}
const Create: React.FC<ProjectCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.code == '')
                    errorMessages.push("code is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new ProjectDto();
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
        } = useCreateHook<ProjectDto, ProjectCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [templates, setTemplates] = useState<TemplateDto[]>([]);
    const [projectStates, setProjectStates] = useState<ProjectStateDto[]>([]);

    const [projectTemplates, setProjectTemplates] = useState<ProjectTemplateDto>(new ProjectTemplateDto());

    const projectStateAdminService = new ProjectStateAdminService();
    const templateAdminService = new TemplateAdminService();
    const projectTemplateAdminService = new ProjectTemplateAdminService();
    useEffect(() => {
        projectStateAdminService.getList().then(({data}) => setProjectStates(data)).catch(error => console.log(error));
        templateAdminService.getList().then(({data}) => {
            const ProjectTemplates = data?.map(prepareProjectTemplate)
            setProjectTemplates(projectTemplates)
        })



    }, []);


    const prepareProjectTemplate = (template: TemplateDto) => {
        const projectTemplate = new ProjectTemplateDto();
        projectTemplate.template = template;
        return projectTemplate;
    }






    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("project.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("project.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="code">{t("project.code")}</label>
                        <InputText id="code" value={item.code} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="name">{t("project.name")}</label>
                        <InputNumber id="name" value={item.name} onChange={(e) => onInputNumerChange(e, 'name')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="generatedDate">{t("project.generatedDate")}</label>
                        <Calendar id="generatedDate" value={item.generatedDate} onChange={(e) => onInputDateChange(e, 'generatedDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="yaml">{t("project.yaml")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="yaml" value={item.yaml} onChange={(e) => onInputTextChange(e, 'yaml')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="projectState">{t("project.projectState")}</label>
                        <Dropdown  id="projectStateDropdown"  value={item.projectState} options={projectStates} onChange={(e) => onDropdownChange(e, 'projectState')}   placeholder={t("project.projectStatePlaceHolder")} filter filterPlaceholder={t("project.projectStatePlaceHolderFilter")} optionLabel="libelle" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="projectTemplates">{t("projectTemplate.template")}</label>
                        {/*
                        <MultiSelect value={item.projectTemplates} options={projectTemplates}  optionLabel="template.libelle" display="chip" placeholder={t("project.projectTemplatesPlaceHolder")}  maxSelectedLabels={3}  onChange={(e) => onMultiSelectChange(e, 'projectTemplates')} />
                        */}
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
