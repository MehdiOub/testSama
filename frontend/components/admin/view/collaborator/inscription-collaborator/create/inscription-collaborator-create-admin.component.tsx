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

import {InscriptionCollaboratorAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorAdminService.service';
import  {InscriptionCollaboratorDto}  from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorCriteria} from "@/controller/criteria/collaborator/InscriptionCollaboratorCriteria.model";

import {CollaboratorDto} from '@/controller/model/collaborator/Collaborator.model';
import {CollaboratorAdminService} from '@/controller/service/admin/collaborator/CollaboratorAdminService.service';
import {PackagingDto} from '@/controller/model/packaging/Packaging.model';
import {PackagingAdminService} from '@/controller/service/admin/packaging/PackagingAdminService.service';
import {InscriptionCollaboratorStateDto} from '@/controller/model/collaborator/InscriptionCollaboratorState.model';
import {InscriptionCollaboratorStateAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorStateAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type InscriptionCollaboratorCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: InscriptionCollaboratorDto) => void,
    showToast: React.Ref<Toast>,
    list: InscriptionCollaboratorDto[],
    service: InscriptionCollaboratorAdminService,
    t: TFunction
}
const Create: React.FC<InscriptionCollaboratorCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
        return errorMessages.length == 0 ;
    }
    const emptyItem = new InscriptionCollaboratorDto();
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
        } = useCreateHook<InscriptionCollaboratorDto, InscriptionCollaboratorCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [collaborators, setCollaborators] = useState<CollaboratorDto[]>([]);
    const [inscriptionCollaboratorStates, setInscriptionCollaboratorStates] = useState<InscriptionCollaboratorStateDto[]>([]);
    const [packagings, setPackagings] = useState<PackagingDto[]>([]);


    const collaboratorAdminService = new CollaboratorAdminService();
    const packagingAdminService = new PackagingAdminService();
    const inscriptionCollaboratorStateAdminService = new InscriptionCollaboratorStateAdminService();
    useEffect(() => {
        packagingAdminService.getList().then(({data}) => setPackagings(data)).catch(error => console.log(error));
        collaboratorAdminService.getList().then(({data}) => setCollaborators(data)).catch(error => console.log(error));
        inscriptionCollaboratorStateAdminService.getList().then(({data}) => setInscriptionCollaboratorStates(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("inscriptionCollaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("inscriptionCollaborator.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="reference">{t("inscriptionCollaborator.reference")}</label>
                        <InputText id="reference" value={item.reference} onChange={(e) => onInputTextChange(e, 'reference')} required className={classNames({'p-invalid': submitted && !item.reference})} />
                        {submitted && !item.reference && <small className="p-invalid">Reference is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="startDate">{t("inscriptionCollaborator.startDate")}</label>
                        <Calendar id="startDate" value={item.startDate} onChange={(e) => onInputDateChange(e, 'startDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="endDate">{t("inscriptionCollaborator.endDate")}</label>
                        <Calendar id="endDate" value={item.endDate} onChange={(e) => onInputDateChange(e, 'endDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="renewDate">{t("inscriptionCollaborator.renewDate")}</label>
                        <Calendar id="renewDate" value={item.renewDate} onChange={(e) => onInputDateChange(e, 'renewDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="packaging">{t("inscriptionCollaborator.packaging")}</label>
                        <Dropdown  id="packagingDropdown"  value={item.packaging} options={packagings} onChange={(e) => onDropdownChange(e, 'packaging')}   placeholder={t("inscriptionCollaborator.packagingPlaceHolder")} filter filterPlaceholder={t("inscriptionCollaborator.packagingPlaceHolderFilter")} optionLabel="code" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="consumedEntity">{t("inscriptionCollaborator.consumedEntity")}</label>
                        <InputNumber id="consumedEntity" value={item.consumedEntity} onChange={(e) => onInputNumerChange(e, 'consumedEntity')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="consumedProjet">{t("inscriptionCollaborator.consumedProjet")}</label>
                        <InputNumber id="consumedProjet" value={item.consumedProjet} onChange={(e) => onInputNumerChange(e, 'consumedProjet')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="consumedAttribut">{t("inscriptionCollaborator.consumedAttribut")}</label>
                        <InputNumber id="consumedAttribut" value={item.consumedAttribut} onChange={(e) => onInputNumerChange(e, 'consumedAttribut')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="consumedIndicator">{t("inscriptionCollaborator.consumedIndicator")}</label>
                        <InputNumber id="consumedIndicator" value={item.consumedIndicator} onChange={(e) => onInputNumerChange(e, 'consumedIndicator')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="collaborator">{t("inscriptionCollaborator.collaborator")}</label>
                        <Dropdown  id="collaboratorDropdown"  value={item.collaborator} options={collaborators} onChange={(e) => onDropdownChange(e, 'collaborator')}   placeholder={t("inscriptionCollaborator.collaboratorPlaceHolder")} filter filterPlaceholder={t("inscriptionCollaborator.collaboratorPlaceHolderFilter")} optionLabel="id" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="inscriptionCollaboratorState">{t("inscriptionCollaborator.inscriptionCollaboratorState")}</label>
                        <Dropdown  id="inscriptionCollaboratorStateDropdown"  value={item.inscriptionCollaboratorState} options={inscriptionCollaboratorStates} onChange={(e) => onDropdownChange(e, 'inscriptionCollaboratorState')}   placeholder={t("inscriptionCollaborator.inscriptionCollaboratorStatePlaceHolder")} filter filterPlaceholder={t("inscriptionCollaborator.inscriptionCollaboratorStatePlaceHolderFilter")} optionLabel="libelle" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
