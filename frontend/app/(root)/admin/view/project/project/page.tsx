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


import {ProjectAdminService} from '@/controller/service/admin/project/ProjectAdminService.service';
import {ProjectDto}  from '@/controller/model/project/Project.model';
import {ProjectCriteria} from '@/controller/criteria/project/ProjectCriteria.model';

import {ProjectStateDto} from '@/controller/model/project/ProjectState.model';
import {ProjectStateAdminService} from '@/controller/service/admin/project/ProjectStateAdminService.service';


import Edit from '@/components/admin/view/project/project/edit/project-edit-admin.component';
import Create from '@/components/admin/view/project/project/create/project-create-admin.component';
import View from '@/components/admin/view/project/project/view/project-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new ProjectDto();
    const emptyCriteria = new ProjectCriteria();
    const service = new ProjectAdminService();


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
    } = useListHook<ProjectDto, ProjectCriteria>({ emptyItem, emptyCriteria,service, t})



    const [projectStates, setProjectStates] = useState<ProjectStateDto[]>([]);

    const projectStateAdminService = new ProjectStateAdminService();

    useEffect(() => {
        activateSecurityConstraint("Project")
        projectStateAdminService.getList().then(({data}) => setProjectStates(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("project.header")}</h5>
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
                                <label className="mb-1"  htmlFor="1">{t("project.code")}</label>
                                <InputText id="1" value={criteria.code} onChange={(e) => setCriteria({ ...criteria, code: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-1">{t("project.generatedDateMin")}</label>
                                <Calendar id="3-1" value={criteria.generatedDateFrom} onChange={(e) => setCriteria({ ...criteria, generatedDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3-2">{t("project.generatedDateMax")}</label>
                                <Calendar id="3-2" value={criteria.generatedDateTo} onChange={(e) => setCriteria({ ...criteria, generatedDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4">{t("project.yaml")}</label>
                                <InputText id="4" value={criteria.yaml} onChange={(e) => setCriteria({ ...criteria, yaml: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5">{t("project.projectStatePlaceHolder")}</label>
                                <Dropdown id="5" value={criteria.projectState} options={projectStates} onChange={(e) => setCriteria({ ...criteria, projectState: e.target.value })} optionLabel="libelle" filter showClear/>
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
                    
                    <Column field="code" header={t("project.code")} sortable></Column>
                    
                    
                    <Column field="name" header={t("project.name")} sortable></Column>
                    
                    
                    <Column field="generatedDate" header={t("project.generatedDate")} sortable body={formateDate("generatedDate")}></Column>
                    
                    
                    <Column field="projectState.libelle" header={t("project.projectState")} sortable ></Column>
                    
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
                    {item && (<span>{t("project.deleteProjectConfirmationMessage")} <b>{item.code}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("project.deleteProjectsConfirmationMessage")}</span>}
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

