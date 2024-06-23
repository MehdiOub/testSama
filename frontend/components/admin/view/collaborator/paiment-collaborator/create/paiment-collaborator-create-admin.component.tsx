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

import {PaimentCollaboratorAdminService} from '@/controller/service/admin/collaborator/PaimentCollaboratorAdminService.service';
import  {PaimentCollaboratorDto}  from '@/controller/model/collaborator/PaimentCollaborator.model';
import {PaimentCollaboratorCriteria} from "@/controller/criteria/collaborator/PaimentCollaboratorCriteria.model";

import {PaimentCollaboratorStateDto} from '@/controller/model/collaborator/PaimentCollaboratorState.model';
import {PaimentCollaboratorStateAdminService} from '@/controller/service/admin/collaborator/PaimentCollaboratorStateAdminService.service';
import {InscriptionCollaboratorDto} from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorAdminService.service';
import {TFunction} from "i18next";
import {Toast} from "primereact/toast";
import useCreateHook from "@/utils/zyhook/useCreate.hook";



type PaimentCollaboratorCreateAdminType = {
    visible: boolean,
    onClose: () => void,
    add: (item: PaimentCollaboratorDto) => void,
    showToast: React.Ref<Toast>,
    list: PaimentCollaboratorDto[],
    service: PaimentCollaboratorAdminService,
    t: TFunction
}
const Create: React.FC<PaimentCollaboratorCreateAdminType> = ({visible, onClose, add, showToast, list, service, t}) => {


    const isFormValid = () => {
    let errorMessages = new Array<string>();
                if(item.libelle == '')
                    errorMessages.push("libelle is required")
                if(item.code == '')
                    errorMessages.push("code is required")
        return errorMessages.length == 0 ;
    }
    const emptyItem = new PaimentCollaboratorDto();
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
        } = useCreateHook<PaimentCollaboratorDto, PaimentCollaboratorCriteria>({list, emptyItem, onClose, add, showToast,service, isFormValid})
    const [inscriptionCollaborators, setInscriptionCollaborators] = useState<InscriptionCollaboratorDto[]>([]);
    const [paimentCollaboratorStates, setPaimentCollaboratorStates] = useState<PaimentCollaboratorStateDto[]>([]);


    const paimentCollaboratorStateAdminService = new PaimentCollaboratorStateAdminService();
    const inscriptionCollaboratorAdminService = new InscriptionCollaboratorAdminService();
    useEffect(() => {
        inscriptionCollaboratorAdminService.getList().then(({data}) => setInscriptionCollaborators(data)).catch(error => console.log(error));
        paimentCollaboratorStateAdminService.getList().then(({data}) => setPaimentCollaboratorStates(data)).catch(error => console.log(error));
    }, []);








    const itemDialogFooter = ( <>
        <Button label={t("cancel")} icon="pi pi-times" text onClick={hideDialog} />
        <Button label={t("save")} icon="pi pi-check" onClick={saveItem} /> </>
    );

return(
        <Dialog visible={visible} style={{width: '70vw'}} header={t("paimentCollaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog} >
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("paimentCollaborator.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="libelle">{t("paimentCollaborator.libelle")}</label>
                        <InputText id="libelle" value={item.libelle} onChange={(e) => onInputTextChange(e, 'libelle')} required className={classNames({'p-invalid': submitted && !item.libelle})} />
                        {submitted && !item.libelle && <small className="p-invalid">Libelle is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("paimentCollaborator.description")}</label>
                        <span className="p-float-label">
                        <InputTextarea id="description" value={item.description} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                    </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="code">{t("paimentCollaborator.code")}</label>
                        <InputText id="code" value={item.code} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="amountToPaid">{t("paimentCollaborator.amountToPaid")}</label>
                        <InputNumber id="amountToPaid" value={item.amountToPaid} onChange={(e) => onInputNumerChange(e, 'amountToPaid')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="total">{t("paimentCollaborator.total")}</label>
                        <InputNumber id="total" value={item.total} onChange={(e) => onInputNumerChange(e, 'total')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="discount">{t("paimentCollaborator.discount")}</label>
                        <InputNumber id="discount" value={item.discount} onChange={(e) => onInputNumerChange(e, 'discount')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="remaining">{t("paimentCollaborator.remaining")}</label>
                        <InputNumber id="remaining" value={item.remaining} onChange={(e) => onInputNumerChange(e, 'remaining')} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paiementDate">{t("paimentCollaborator.paiementDate")}</label>
                        <Calendar id="paiementDate" value={item.paiementDate} onChange={(e) => onInputDateChange(e, 'paiementDate')} dateFormat="dd/mm/yy"  showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="inscriptionCollaborator">{t("paimentCollaborator.inscriptionCollaborator")}</label>
                        <Dropdown  id="inscriptionCollaboratorDropdown"  value={item.inscriptionCollaborator} options={inscriptionCollaborators} onChange={(e) => onDropdownChange(e, 'inscriptionCollaborator')}   placeholder={t("paimentCollaborator.inscriptionCollaboratorPlaceHolder")} filter filterPlaceholder={t("paimentCollaborator.inscriptionCollaboratorPlaceHolderFilter")} optionLabel="reference" showClear/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paimentCollaboratorState">{t("paimentCollaborator.paimentCollaboratorState")}</label>
                        <Dropdown  id="paimentCollaboratorStateDropdown"  value={item.paimentCollaboratorState} options={paimentCollaboratorStates} onChange={(e) => onDropdownChange(e, 'paimentCollaboratorState')}   placeholder={t("paimentCollaborator.paimentCollaboratorStatePlaceHolder")} filter filterPlaceholder={t("paimentCollaborator.paimentCollaboratorStatePlaceHolderFilter")} optionLabel="libelle" showClear/>
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Create;
