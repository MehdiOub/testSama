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


import {PaimentCollaboratorAdminService} from '@/controller/service/admin/collaborator/PaimentCollaboratorAdminService.service';
import  {PaimentCollaboratorDto}  from '@/controller/model/collaborator/PaimentCollaborator.model';
import {PaimentCollaboratorCriteria} from "@/controller/criteria/collaborator/PaimentCollaboratorCriteria.model";


import {PaimentCollaboratorStateDto} from '@/controller/model/collaborator/PaimentCollaboratorState.model';
import {PaimentCollaboratorStateAdminService} from '@/controller/service/admin/collaborator/PaimentCollaboratorStateAdminService.service';
import {InscriptionCollaboratorDto} from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorAdminService.service';


type PaimentCollaboratorEditAdminType = {
    visible: boolean,
    onClose: () => void,
    showToast: React.Ref<Toast>,
    selectedItem: PaimentCollaboratorDto
    update: (item: PaimentCollaboratorDto) => void,
    list: PaimentCollaboratorDto[],
    service: PaimentCollaboratorAdminService,
    t: TFunction
}
const Edit: React.FC<PaimentCollaboratorEditAdminType> = ({visible, onClose, showToast, selectedItem, update, list, service, t}) => {


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
        onDropdownChange,
        onTabChange,
        hideDialog,
        editItem,
        formateDate,
        parseToIsoFormat,
        adaptDate
        } = useEditHook<PaimentCollaboratorDto, PaimentCollaboratorCriteria>({list, selectedItem, onClose, update, showToast,service, t, isFormValid})

    const [inscriptionCollaborators, setInscriptionCollaborators] = useState<InscriptionCollaboratorDto[]>([]);
    const [paimentCollaboratorStates, setPaimentCollaboratorStates] = useState<PaimentCollaboratorStateDto[]>([]);


    const paimentCollaboratorStateAdminService = new PaimentCollaboratorStateAdminService();
    const inscriptionCollaboratorAdminService = new InscriptionCollaboratorAdminService();
    useEffect(() => {
    inscriptionCollaboratorAdminService.getList().then(({data}) => setInscriptionCollaborators(data)).catch(error => console.log(error));
    paimentCollaboratorStateAdminService.getList().then(({data}) => setPaimentCollaboratorStates(data)).catch(error => console.log(error));


        }, []);







    const itemDialogFooter = ( <>
        <Button label="Cancel" icon="pi pi-times" text onClick={hideDialog} />
        <Button label="Save" icon="pi pi-check" onClick={editItem} /> </>
    );



    return(
    <Dialog visible={visible} style={{width: '70vw'}} header={t("paimentCollaborator.tabPan")} modal className="p-fluid" footer={itemDialogFooter} onHide={hideDialog}>
        <TabView activeIndex={activeIndex} onTabChange={onTabChange}>
            <TabPanel header={t("paimentCollaborator.tabPan")}>
                <div className="formgrid grid">
                    <div className="field col-6">
                        <label htmlFor="libelle">{t("paimentCollaborator.libelle")}</label>
                        <InputText id="libelle" value={item ? item.libelle : ''} onChange={(e) => onInputTextChange(e, 'libelle')} required className={classNames({'p-invalid': submitted && !item.libelle})} />
                        {submitted && !item.libelle && <small className="p-invalid">Libelle is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="description">{t("paimentCollaborator.description")}</label>
                        <span className="p-float-label">
                            <InputTextarea id="description" value={item ? item.description : ''} onChange={(e) => onInputTextChange(e, 'description')} rows={5} cols={30}/>
                        </span>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="code">{t("paimentCollaborator.code")}</label>
                        <InputText id="code" value={item ? item.code : ''} onChange={(e) => onInputTextChange(e, 'code')} required className={classNames({'p-invalid': submitted && !item.code})} />
                        {submitted && !item.code && <small className="p-invalid">Code is required.</small>}
                    </div>
                    <div className="field col-6">
                        <label htmlFor="amountToPaid">{t("paimentCollaborator.amountToPaid")}</label>
                        <InputNumber id="amountToPaid" value={item ? item.amountToPaid : 0} onChange={(e) => onInputNumerChange(e, 'amountToPaid')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="total">{t("paimentCollaborator.total")}</label>
                        <InputNumber id="total" value={item ? item.total : 0} onChange={(e) => onInputNumerChange(e, 'total')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="discount">{t("paimentCollaborator.discount")}</label>
                        <InputNumber id="discount" value={item ? item.discount : 0} onChange={(e) => onInputNumerChange(e, 'discount')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="remaining">{t("paimentCollaborator.remaining")}</label>
                        <InputNumber id="remaining" value={item ? item.remaining : 0} onChange={(e) => onInputNumerChange(e, 'remaining')}/>
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paiementDate">{t("paimentCollaborator.paiementDate")}</label>
                        <Calendar id="paiementDate" value={adaptDate(item?.paiementDate)} onChange={(e) => onInputDateChange(e, 'paiementDate')} dateFormat="dd/mm/yy" showIcon={true} />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="inscriptionCollaborator">{t("paimentCollaborator.inscriptionCollaborator")}</label>
                        <Dropdown  id="inscriptionCollaboratorDropdown"  value={item ? item.inscriptionCollaborator : ''} options={inscriptionCollaborators} onChange={(e) => onDropdownChange(e, 'inscriptionCollaborator')}   placeholder="Sélectionnez un inscriptionCollaborator" filter filterPlaceholder="Rechercher un inscriptionCollaborator" optionLabel="reference" showClear />
                    </div>
                    <div className="field col-6">
                        <label htmlFor="paimentCollaboratorState">{t("paimentCollaborator.paimentCollaboratorState")}</label>
                        <Dropdown  id="paimentCollaboratorStateDropdown"  value={item ? item.paimentCollaboratorState : ''} options={paimentCollaboratorStates} onChange={(e) => onDropdownChange(e, 'paimentCollaboratorState')}   placeholder="Sélectionnez un paimentCollaboratorState" filter filterPlaceholder="Rechercher un paimentCollaboratorState" optionLabel="libelle" showClear />
                    </div>
                </div>
            </TabPanel>
        </TabView>
    </Dialog>
);
};
export default Edit;


