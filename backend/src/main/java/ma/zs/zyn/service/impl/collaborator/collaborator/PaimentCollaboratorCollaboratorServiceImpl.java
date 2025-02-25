package ma.zs.zyn.service.impl.collaborator.collaborator;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.PaimentCollaboratorCriteria;
import ma.zs.zyn.dao.facade.core.collaborator.PaimentCollaboratorDao;
import ma.zs.zyn.dao.specification.core.collaborator.PaimentCollaboratorSpecification;
import ma.zs.zyn.service.facade.collaborator.collaborator.PaimentCollaboratorCollaboratorService;
import ma.zs.zyn.zynerator.service.AbstractServiceImpl;
import static ma.zs.zyn.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zs.zyn.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.zyn.service.facade.collaborator.collaborator.PaimentCollaboratorStateCollaboratorService ;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState ;
import ma.zs.zyn.service.facade.collaborator.collaborator.InscriptionCollaboratorCollaboratorService ;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator ;

import java.util.List;
@Service
public class PaimentCollaboratorCollaboratorServiceImpl implements PaimentCollaboratorCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator update(PaimentCollaborator t) {
        PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaimentCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaimentCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaimentCollaborator findOrSave(PaimentCollaborator t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaimentCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaimentCollaborator> findAll() {
        return dao.findAll();
    }

    public List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria) {
        List<PaimentCollaborator> content = null;
        if (criteria != null) {
            PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PaimentCollaboratorSpecification constructSpecification(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification =  (PaimentCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaimentCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaimentCollaboratorCriteria criteria) {
        PaimentCollaboratorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id){
        return dao.findByInscriptionCollaboratorId(id);
    }
    public int deleteByInscriptionCollaboratorId(Long id){
        return dao.deleteByInscriptionCollaboratorId(id);
    }
    public long countByInscriptionCollaboratorReference(String reference){
        return dao.countByInscriptionCollaboratorReference(reference);
    }
    public List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code){
        return dao.findByPaimentCollaboratorStateCode(code);
    }
    public int deleteByPaimentCollaboratorStateCode(String code){
        return dao.deleteByPaimentCollaboratorStateCode(code);
    }
    public long countByPaimentCollaboratorStateCode(String code){
        return dao.countByPaimentCollaboratorStateCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> delete(List<PaimentCollaborator> list) {
		List<PaimentCollaborator> result = new ArrayList();
        if (list != null) {
            for (PaimentCollaborator t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaimentCollaborator create(PaimentCollaborator t) {
        PaimentCollaborator loaded = findByReferenceEntity(t);
        PaimentCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaimentCollaborator findWithAssociatedLists(Long id){
        PaimentCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaimentCollaborator> update(List<PaimentCollaborator> ts, boolean createIfNotExist) {
        List<PaimentCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (PaimentCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaimentCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaimentCollaborator t, PaimentCollaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaimentCollaborator findByReferenceEntity(PaimentCollaborator t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(PaimentCollaborator t){
        if( t != null) {
            t.setInscriptionCollaborator(inscriptionCollaboratorService.findOrSave(t.getInscriptionCollaborator()));
        }
    }



    public List<PaimentCollaborator> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList) {
        List<List<PaimentCollaborator>> result = new ArrayList<>();
        List<PaimentCollaborator> resultDelete = new ArrayList<>();
        List<PaimentCollaborator> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList, List<PaimentCollaborator> resultUpdateOrSave, List<PaimentCollaborator> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaimentCollaborator myOld = oldList.get(i);
                PaimentCollaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaimentCollaborator myNew = newList.get(i);
                PaimentCollaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private PaimentCollaboratorStateCollaboratorService paimentCollaboratorStateService ;
    @Autowired
    private InscriptionCollaboratorCollaboratorService inscriptionCollaboratorService ;

    public PaimentCollaboratorCollaboratorServiceImpl(PaimentCollaboratorDao dao) {
        this.dao = dao;
    }

    private PaimentCollaboratorDao dao;
}
