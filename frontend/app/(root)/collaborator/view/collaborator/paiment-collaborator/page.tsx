"use client"
import {Button} from 'primereact/button';
import {Column} from 'primereact/column';


import {DataTable} from 'primereact/datatable';
import {Dialog} from 'primereact/dialog';
import {FileUpload} from 'primereact/fileupload';
import {InputText} from 'primereact/inputtext';
import {Toast} from 'primereact/toast';
import {Toolbar} from 'primereact/toolbar';
import React, {useEffect, useRef, useState} from 'react';
import {Paginator, PaginatorPageChangeEvent} from 'primereact/paginator';
import {Card} from 'primereact/card';
import {Calendar} from 'primereact/calendar';
import {InputNumber} from 'primereact/inputnumber';
import {Dropdown} from 'primereact/dropdown';
import {format} from "date-fns";
import { useTranslation } from 'react-i18next';

import useListHook from "@/utils/zyhook/useListhook";


import {PaimentCollaboratorCollaboratorService} from '@/controller/service/collaborator/collaborator/PaimentCollaboratorCollaboratorService.service';
import {PaimentCollaboratorDto}  from '@/controller/model/collaborator/PaimentCollaborator.model';
import {PaimentCollaboratorCriteria} from '@/controller/criteria/collaborator/PaimentCollaboratorCriteria.model';

import {InscriptionCollaboratorDto} from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorCollaboratorService} from '@/controller/service/collaborator/collaborator/InscriptionCollaboratorCollaboratorService.service';
import {PaimentCollaboratorStateDto} from '@/controller/model/collaborator/PaimentCollaboratorState.model';
import {PaimentCollaboratorStateCollaboratorService} from '@/controller/service/collaborator/collaborator/PaimentCollaboratorStateCollaboratorService.service';


import Edit from '@/components/collaborator/view/collaborator/paiment-collaborator/edit/paiment-collaborator-edit-collaborator.component';
import Create from '@/components/collaborator/view/collaborator/paiment-collaborator/create/paiment-collaborator-create-collaborator.component';
import View from '@/components/collaborator/view/collaborator/paiment-collaborator/view/paiment-collaborator-view-collaborator.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new PaimentCollaboratorDto();
    const emptyCriteria = new PaimentCollaboratorCriteria();
    const service = new PaimentCollaboratorCollaboratorService();


    const {
        items,
        showSearch,
        deleteItemDialog,
        item,
        selectedItems,
        setSelectedItems,
        hideDeleteItemDialog,
        globalFilter,
        setGlobalFilter,
        showCreateDialog,
        setShowCreateDialog,
        showEditDialog,
        setShowEditDialog,
        showViewDialog,
        setShowViewDialog,
        selectedItem,
        setSelectedItem,
        rows,
        totalRecords,
        criteria,
        setCriteria,
        first,
        fetchItems,
        toast,
        dt,
        findByCriteriaShow,
        handleCancelClick,
        confirmDeleteSelected,
        exportCSV,
        deleteItem,
        deleteItemDialogFooter,
        leftToolbarTemplate,
        rightToolbarTemplate,
        actionBodyTemplate,
        CustomBooleanCell,
        handleValidateClick,
        onPage,
        showCreateModal,
        showEditModal,
        showViewModal,
        add,
        update,
        confirmDeleteItem,
        statusBodyTemplate,
        formateDate,
        deleteSelectedItems,
        deleteItemsDialog,
        deleteItemsDialogFooter,
        hideDeleteItemsDialog,
        filter,
        activateSecurityConstraint,
        listPremisions,
    } = useListHook<PaimentCollaboratorDto, PaimentCollaboratorCriteria>({ emptyItem, emptyCriteria,service, t})



    const [inscriptionCollaborators, setInscriptionCollaborators] = useState<InscriptionCollaboratorDto[]>([]);
    const [paimentCollaboratorStates, setPaimentCollaboratorStates] = useState<PaimentCollaboratorStateDto[]>([]);

    const inscriptionCollaboratorCollaboratorService = new InscriptionCollaboratorCollaboratorService();
    const paimentCollaboratorStateCollaboratorService = new PaimentCollaboratorStateCollaboratorService();

    useEffect(() => {
        activateSecurityConstraint("PaimentCollaborator")
        inscriptionCollaboratorCollaboratorService.getList().then(({data}) => setInscriptionCollaborators(data)).catch(error => console.log(error));
        paimentCollaboratorStateCollaboratorService.getList().then(({data}) => setPaimentCollaboratorStates(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("paimentCollaborator.header")}</h5>
        <span className="block mt-2 md:mt-0 p-input-icon-left"><i className="pi pi-search"/>
            <InputText type="search" onInput={(e) => filter(e)}
                       placeholder={t("search")}/> </span>
    </div>
    );
    return (
    <div className="grid crud-demo">
        {listPremisions ?(
        <div className="col-12">
            <div className="card">
                <Toast ref={toast} />
                <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>
                {findByCriteriaShow && (
                <Card title={t("search")} className="mb-5">
                        <div className="grid">
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="1">{t("paimentCollaborator.libelle")}</label>
                                <InputText id="1" value={criteria.libelle} onChange={(e) => setCriteria({ ...criteria, libelle: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2">{t("paimentCollaborator.description")}</label>
                                <InputText id="2" value={criteria.description} onChange={(e) => setCriteria({ ...criteria, description: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3">{t("paimentCollaborator.code")}</label>
                                <InputText id="3" value={criteria.code} onChange={(e) => setCriteria({ ...criteria, code: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-1">{t("paimentCollaborator.amountToPaidMin")}</label>
                                <InputNumber id="4-1" value={criteria.amountToPaidMin} onChange={(e) => setCriteria({ ...criteria, amountToPaidMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-2">{t("paimentCollaborator.amountToPaidMax")}  </label>
                                <InputNumber id="4-2" value={criteria.amountToPaidMax} onChange={(e) => setCriteria({ ...criteria, amountToPaidMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-1">{t("paimentCollaborator.totalMin")}</label>
                                <InputNumber id="5-1" value={criteria.totalMin} onChange={(e) => setCriteria({ ...criteria, totalMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-2">{t("paimentCollaborator.totalMax")}  </label>
                                <InputNumber id="5-2" value={criteria.totalMax} onChange={(e) => setCriteria({ ...criteria, totalMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-1">{t("paimentCollaborator.discountMin")}</label>
                                <InputNumber id="6-1" value={criteria.discountMin} onChange={(e) => setCriteria({ ...criteria, discountMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-2">{t("paimentCollaborator.discountMax")}  </label>
                                <InputNumber id="6-2" value={criteria.discountMax} onChange={(e) => setCriteria({ ...criteria, discountMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-1">{t("paimentCollaborator.remainingMin")}</label>
                                <InputNumber id="7-1" value={criteria.remainingMin} onChange={(e) => setCriteria({ ...criteria, remainingMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-2">{t("paimentCollaborator.remainingMax")}  </label>
                                <InputNumber id="7-2" value={criteria.remainingMax} onChange={(e) => setCriteria({ ...criteria, remainingMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8-1">{t("paimentCollaborator.paiementDateMin")}</label>
                                <Calendar id="8-1" value={criteria.paiementDateFrom} onChange={(e) => setCriteria({ ...criteria, paiementDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8-2">{t("paimentCollaborator.paiementDateMax")}</label>
                                <Calendar id="8-2" value={criteria.paiementDateTo} onChange={(e) => setCriteria({ ...criteria, paiementDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="9">{t("paimentCollaborator.inscriptionCollaboratorPlaceHolder")}</label>
                                <Dropdown id="9" value={criteria.inscriptionCollaborator} options={inscriptionCollaborators} onChange={(e) => setCriteria({ ...criteria, inscriptionCollaborator: e.target.value })} optionLabel="reference" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="10">{t("paimentCollaborator.paimentCollaboratorStatePlaceHolder")}</label>
                                <Dropdown id="10" value={criteria.paimentCollaboratorState} options={paimentCollaboratorStates} onChange={(e) => setCriteria({ ...criteria, paimentCollaboratorState: e.target.value })} optionLabel="libelle" filter showClear/>
                            </div>
                        </div>
                        <div style={{ marginTop : '1rem', display: 'flex', justifyContent: 'flex-end' }} >
                            <Button label={t("validate")} icon="pi pi-sort-amount-down" className="p-button-info mr-2" onClick={handleValidateClick} />
                            <Button label={t("cancel")} className="p-button-secondary mr-2"  icon="pi pi-times" onClick={handleCancelClick} />
                        </div>
                </Card>
                )}
                <DataTable ref={dt} value={items} selection={selectedItems as any} onSelectionChange={(e) => setSelectedItems(e.value as any)} dataKey="id" className="datatable-responsive" filters={globalFilter} header={header} responsiveLayout="scroll" >
                    <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}> </Column>
                    
                    <Column field="libelle" header={t("paimentCollaborator.libelle")} sortable></Column>
                    
                    
                    <Column field="code" header={t("paimentCollaborator.code")} sortable></Column>
                    
                    
                    <Column field="amountToPaid" header={t("paimentCollaborator.amountToPaid")} sortable></Column>
                    
                    
                    <Column field="total" header={t("paimentCollaborator.total")} sortable></Column>
                    
                    
                    <Column field="discount" header={t("paimentCollaborator.discount")} sortable></Column>
                    
                    
                    <Column field="remaining" header={t("paimentCollaborator.remaining")} sortable></Column>
                    
                    
                    <Column field="paiementDate" header={t("paimentCollaborator.paiementDate")} sortable body={formateDate("paiementDate")}></Column>
                    
                    
                    <Column field="inscriptionCollaborator.reference" header={t("paimentCollaborator.inscriptionCollaborator")} sortable ></Column>
                    
                    
                    <Column field="paimentCollaboratorState.libelle" header={t("paimentCollaborator.paimentCollaboratorState")} sortable ></Column>
                    
                    <Column header={t("actions")} body={actionBodyTemplate}></Column>
                </DataTable>
                <div className="p-d-flex p-ai-center p-jc-between">
                    <Paginator onPageChange={onPage} first={first} rows={rows} totalRecords={totalRecords} />
                </div>
                {showCreateDialog && <Create visible={showCreateDialog} onClose={() => setShowCreateDialog(false)} add={add} showToast={toast} list={items} service={service} t={t} />}

                {showEditDialog && <Edit  visible={showEditDialog} onClose={() =>  { setShowEditDialog(false); setSelectedItem(emptyItem); }} showToast={toast} selectedItem={selectedItem} update={update} list={items} service={service}   t={t} />}

                {showViewDialog && <View visible={showViewDialog} onClose={() =>  { setShowViewDialog(false); setSelectedItem(emptyItem); }} selectedItem={selectedItem}   t={t} />}
                <Dialog visible={deleteItemDialog} style={{width: '450px'}} header={t("confirm")} modal footer={deleteItemDialogFooter} onHide={hideDeleteItemDialog}>
                    <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{fontSize: '2rem'}}/>
                    {item && (<span>{t("paimentCollaborator.deletePaimentCollaboratorConfirmationMessage")} <b>{item.libelle}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("paimentCollaborator.deletePaimentCollaboratorsConfirmationMessage")}</span>}
                </div>
            </Dialog>

            </div>
        </div>

        ):(
        <div className="card" style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
            <p style={{alignItems:"center",display:"flex",justifyContent:"center"}}>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" style={{width:"90px",height:"90px", color:"red"}}>
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126ZM12 15.75h.007v.008H12v-.008Z" />
                </svg>
                Opess You don t have permission to access !
            </p>
        </div>
        )}
    </div>
    );
    };
export default List;

