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


import {TemplateAdminService} from '@/controller/service/admin/template/TemplateAdminService.service';
import {TemplateDto}  from '@/controller/model/template/Template.model';
import {TemplateCriteria} from '@/controller/criteria/template/TemplateCriteria.model';

import {TechnologyDto} from '@/controller/model/template/Technology.model';
import {TechnologyAdminService} from '@/controller/service/admin/template/TechnologyAdminService.service';


import Edit from '@/components/admin/view/template/template/edit/template-edit-admin.component';
import Create from '@/components/admin/view/template/template/create/template-create-admin.component';
import View from '@/components/admin/view/template/template/view/template-view-admin.component';



const List = () => {

    const { t } = useTranslation();

    const emptyItem = new TemplateDto();
    const emptyCriteria = new TemplateCriteria();
    const service = new TemplateAdminService();


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
    } = useListHook<TemplateDto, TemplateCriteria>({ emptyItem, emptyCriteria,service, t})



    const [technologys, setTechnologys] = useState<TechnologyDto[]>([]);

    const technologyAdminService = new TechnologyAdminService();

    useEffect(() => {
        activateSecurityConstraint("Template")
        technologyAdminService.getList().then(({data}) => setTechnologys(data)).catch(error => console.log(error));
        fetchItems(criteria);
    }, []);



    const header = (
    <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">{t("template.header")}</h5>
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
                                <label className="mb-1"  htmlFor="1">{t("template.code")}</label>
                                <InputText id="1" value={criteria.code} onChange={(e) => setCriteria({ ...criteria, code: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="2">{t("template.libelle")}</label>
                                <InputText id="2" value={criteria.libelle} onChange={(e) => setCriteria({ ...criteria, libelle: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="3">{t("template.description")}</label>
                                <InputText id="3" value={criteria.description} onChange={(e) => setCriteria({ ...criteria, description: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-1">{t("template.addingDateMin")}</label>
                                <Calendar id="4-1" value={criteria.addingDateFrom} onChange={(e) => setCriteria({ ...criteria, addingDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="4-2">{t("template.addingDateMax")}</label>
                                <Calendar id="4-2" value={criteria.addingDateTo} onChange={(e) => setCriteria({ ...criteria, addingDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-1">{t("template.lastUpdateDateMin")}</label>
                                <Calendar id="5-1" value={criteria.lastUpdateDateFrom} onChange={(e) => setCriteria({ ...criteria, lastUpdateDateFrom: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="5-2">{t("template.lastUpdateDateMax")}</label>
                                <Calendar id="5-2" value={criteria.lastUpdateDateTo} onChange={(e) => setCriteria({ ...criteria, lastUpdateDateTo: e.value as Date })} dateFormat="dd-MM-yy" showIcon={true} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="6">{t("template.templateTags")}</label>
                                <InputText id="6" value={criteria.templateTags} onChange={(e) => setCriteria({ ...criteria, templateTags: e.target.value })} />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-1">{t("template.priceMin")}</label>
                                <InputNumber id="7-1" value={criteria.priceMin} onChange={(e) => setCriteria({ ...criteria, priceMin: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="7-2">{t("template.priceMax")}  </label>
                                <InputNumber id="7-2" value={criteria.priceMax} onChange={(e) => setCriteria({ ...criteria, priceMax: e.value })} mode="decimal" />
                            </div>
                            <div className="flex flex-column col-3">
                                <label className="mb-1"  htmlFor="8">{t("template.technologyPlaceHolder")}</label>
                                <Dropdown id="8" value={criteria.technology} options={technologys} onChange={(e) => setCriteria({ ...criteria, technology: e.target.value })} optionLabel="libelle" filter showClear/>
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
                    
                    <Column field="code" header={t("template.code")} sortable></Column>
                    
                    
                    <Column field="libelle" header={t("template.libelle")} sortable></Column>
                    
                    
                    <Column field="addingDate" header={t("template.addingDate")} sortable body={formateDate("addingDate")}></Column>
                    
                    
                    <Column field="lastUpdateDate" header={t("template.lastUpdateDate")} sortable body={formateDate("lastUpdateDate")}></Column>
                    
                    
                    <Column field="templateTags" header={t("template.templateTags")} sortable></Column>
                    
                    
                    <Column field="price" header={t("template.price")} sortable></Column>
                    
                    
                    <Column field="technology.libelle" header={t("template.technology")} sortable ></Column>
                    
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
                    {item && (<span>{t("template.deleteTemplateConfirmationMessage")} <b>{item.libelle}</b>?</span>)}
                    </div>
                </Dialog>

            <Dialog visible={deleteItemsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteItemsDialogFooter} onHide={hideDeleteItemsDialog} >
                <div className="flex align-items-center justify-content-center">
                    <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                    {item && <span>{t("template.deleteTemplatesConfirmationMessage")}</span>}
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

