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


import {InscriptionCollaboratorAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorAdminService.service';
import {InscriptionCollaboratorDto}  from '@/controller/model/collaborator/InscriptionCollaborator.model';
import {InscriptionCollaboratorCriteria} from '@/controller/criteria/collaborator/InscriptionCollaboratorCriteria.model';

import {PackagingDto} from '@/controller/model/packaging/Packaging.model';
import {PackagingAdminService} from '@/controller/service/admin/packaging/PackagingAdminService.service';
import {CollaboratorDto} from '@/controller/model/collaborator/Collaborator.model';
import {CollaboratorAdminService} from '@/controller/service/admin/collaborator/CollaboratorAdminService.service';
import {InscriptionCollaboratorStateDto} from '@/controller/model/collaborator/InscriptionCollaboratorState.model';
import {InscriptionCollaboratorStateAdminService} from '@/controller/service/admin/collaborator/InscriptionCollaboratorStateAdminService.service';


import Edit from '@/components/admin/view/collaborator/inscription-collaborator/edit/inscription-collaborator-edit-admin.component';
import Create from '@/components/admin/view/collaborator/inscription-collaborator/create/inscription-collaborator-create-admin.component';
import View from '@/components/admin/view/collaborator/inscription-collaborator/view/inscription-collaborator-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new InscriptionCollaboratorDto();
    const emptyCriteria = new InscriptionCollaboratorCriteria();
    const service = new InscriptionCollaboratorAdminService();


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
    } = useListHook<InscriptionCollaboratorDto, InscriptionCollaboratorCriteria>({ emptyItem, emptyCriteria,service, t})



    const [packagings, setPackagings] = useState<PackagingDto[]>([]);
    const [collaborators, setCollaborators] = useState<CollaboratorDto[]>([]);
    const [inscriptionCollaboratorStates, setInscriptionCollaboratorStates] = useState<InscriptionCollaboratorStateDto[]>([]);

    const packagingAdminService = new PackagingAdminService();
    const collaboratorAdminService = new CollaboratorAdminService();
    const inscriptionCollaboratorStateAdminService = new InscriptionCollaboratorStateAdminService();

    useEffect(() => {
        activateSecurityConstraint("InscriptionCollaborator")
        packagingAdminService.getList().then(({data}) => setPackagings(data)).catch(error => console.log(error));
        collaboratorAdminService.getList().then(({data}) => setCollaborators(data)).catch(error => console.log(error));
        inscriptionCollaboratorStateAdminService.getList().then(({data}) => setInscriptionCollaboratorStates(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("inscriptionCollaborator.header")}</h5>
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
                                <label className="mb-1"  htmlFor="1">{t("inscriptionCollaborator.reference")}</label>
                                <InputText id="1" value={criteria.reference} onChange={(e) => setCriteria({ ...criteria, reference: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2-1">{t("inscriptionCollaborator.startDateMin")}</label>
                                <Calendar id="2-1" value={criteria.startDateFrom} onChange={(e) => setCriteria({ ...criteria, startDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2-2">{t("inscriptionCollaborator.startDateMax")}</label>
                                <Calendar id="2-2" value={criteria.startDateTo} onChange={(e) => setCriteria({ ...criteria, startDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-1">{t("inscriptionCollaborator.endDateMin")}</label>
                                <Calendar id="3-1" value={criteria.endDateFrom} onChange={(e) => setCriteria({ ...criteria, endDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-2">{t("inscriptionCollaborator.endDateMax")}</label>
                                <Calendar id="3-2" value={criteria.endDateTo} onChange={(e) => setCriteria({ ...criteria, endDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-1">{t("inscriptionCollaborator.renewDateMin")}</label>
                                <Calendar id="4-1" value={criteria.renewDateFrom} onChange={(e) => setCriteria({ ...criteria, renewDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-2">{t("inscriptionCollaborator.renewDateMax")}</label>
                                <Calendar id="4-2" value={criteria.renewDateTo} onChange={(e) => setCriteria({ ...criteria, renewDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5">{t("inscriptionCollaborator.packagingPlaceHolder")}</label>
                                <Dropdown id="5" value={criteria.packaging} options={packagings} onChange={(e) => setCriteria({ ...criteria, packaging: e.target.value })} optionLabel="code" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-1">{t("inscriptionCollaborator.consumedEntityMin")}</label>
                                <InputNumber id="6-1" value={criteria.consumedEntityMin} onChange={(e) => setCriteria({ ...criteria, consumedEntityMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6-2">{t("inscriptionCollaborator.consumedEntityMax")}  </label>
                                <InputNumber id="6-2" value={criteria.consumedEntityMax} onChange={(e) => setCriteria({ ...criteria, consumedEntityMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-1">{t("inscriptionCollaborator.consumedProjetMin")}</label>
                                <InputNumber id="7-1" value={criteria.consumedProjetMin} onChange={(e) => setCriteria({ ...criteria, consumedProjetMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-2">{t("inscriptionCollaborator.consumedProjetMax")}  </label>
                                <InputNumber id="7-2" value={criteria.consumedProjetMax} onChange={(e) => setCriteria({ ...criteria, consumedProjetMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8-1">{t("inscriptionCollaborator.consumedAttributMin")}</label>
                                <InputNumber id="8-1" value={criteria.consumedAttributMin} onChange={(e) => setCriteria({ ...criteria, consumedAttributMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8-2">{t("inscriptionCollaborator.consumedAttributMax")}  </label>
                                <InputNumber id="8-2" value={criteria.consumedAttributMax} onChange={(e) => setCriteria({ ...criteria, consumedAttributMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="9-1">{t("inscriptionCollaborator.consumedIndicatorMin")}</label>
                                <InputNumber id="9-1" value={criteria.consumedIndicatorMin} onChange={(e) => setCriteria({ ...criteria, consumedIndicatorMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="9-2">{t("inscriptionCollaborator.consumedIndicatorMax")}  </label>
                                <InputNumber id="9-2" value={criteria.consumedIndicatorMax} onChange={(e) => setCriteria({ ...criteria, consumedIndicatorMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="10">{t("inscriptionCollaborator.collaboratorPlaceHolder")}</label>
                                <Dropdown id="10" value={criteria.collaborator} options={collaborators} onChange={(e) => setCriteria({ ...criteria, collaborator: e.target.value })} optionLabel="id" filter showClear/>
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="11">{t("inscriptionCollaborator.inscriptionCollaboratorStatePlaceHolder")}</label>
                                <Dropdown id="11" value={criteria.inscriptionCollaboratorState} options={inscriptionCollaboratorStates} onChange={(e) => setCriteria({ ...criteria, inscriptionCollaboratorState: e.target.value })} optionLabel="libelle" filter showClear/>
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
                    
                    <Column field="reference" header={t("inscriptionCollaborator.reference")} sortable></Column>
                    
                    
                    <Column field="startDate" header={t("inscriptionCollaborator.startDate")} sortable body={formateDate("startDate")}></Column>
                    
                    
                    <Column field="endDate" header={t("inscriptionCollaborator.endDate")} sortable body={formateDate("endDate")}></Column>
                    
                    
                    <Column field="renewDate" header={t("inscriptionCollaborator.renewDate")} sortable body={formateDate("renewDate")}></Column>
                    
                    
                    <Column field="packaging.code" header={t("inscriptionCollaborator.packaging")} sortable ></Column>
                    
                    
                    <Column field="consumedEntity" header={t("inscriptionCollaborator.consumedEntity")} sortable></Column>
                    
                    
                    <Column field="consumedProjet" header={t("inscriptionCollaborator.consumedProjet")} sortable></Column>
                    
                    
                    <Column field="consumedAttribut" header={t("inscriptionCollaborator.consumedAttribut")} sortable></Column>
                    
                    
                    <Column field="consumedIndicator" header={t("inscriptionCollaborator.consumedIndicator")} sortable></Column>
                    
                     {/* 
                    <Column field="collaborator.id" header={t("inscriptionCollaborator.collaborator")} sortable ></Column>
                     */} 
                     {/* 
                    <Column field="inscriptionCollaboratorState.libelle" header={t("inscriptionCollaborator.inscriptionCollaboratorState")} sortable ></Column>
                     */} 
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
                    {item && (<span>{t("inscriptionCollaborator.deleteInscriptionCollaboratorConfirmationMessage")} <b>{item.reference}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("inscriptionCollaborator.deleteInscriptionCollaboratorsConfirmationMessage")}</span>}
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

