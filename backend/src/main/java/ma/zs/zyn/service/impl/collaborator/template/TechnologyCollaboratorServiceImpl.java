package ma.zs.zyn.service.impl.collaborator.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.dao.facade.core.template.TechnologyDao;
import ma.zs.zyn.dao.specification.core.template.TechnologySpecification;
import ma.zs.zyn.service.facade.collaborator.template.TechnologyCollaboratorService;
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


import java.util.List;
@Service
public class TechnologyCollaboratorServiceImpl implements TechnologyCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Technology update(Technology t) {
        Technology loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Technology.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Technology findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Technology findOrSave(Technology t) {
        if (t != null) {
            Technology result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Technology> findAll() {
        return dao.findAll();
    }

    public List<Technology> findByCriteria(TechnologyCriteria criteria) {
        List<Technology> content = null;
        if (criteria != null) {
            TechnologySpecification mySpecification = constructSpecification(criteria);
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


    private TechnologySpecification constructSpecification(TechnologyCriteria criteria) {
        TechnologySpecification mySpecification =  (TechnologySpecification) RefelexivityUtil.constructObjectUsingOneParam(TechnologySpecification.class, criteria);
        return mySpecification;
    }

    public List<Technology> findPaginatedByCriteria(TechnologyCriteria criteria, int page, int pageSize, String order, String sortField) {
        TechnologySpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TechnologyCriteria criteria) {
        TechnologySpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<Technology> delete(List<Technology> list) {
		List<Technology> result = new ArrayList();
        if (list != null) {
            for (Technology t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Technology create(Technology t) {
        Technology loaded = findByReferenceEntity(t);
        Technology saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Technology findWithAssociatedLists(Long id){
        Technology result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Technology> update(List<Technology> ts, boolean createIfNotExist) {
        List<Technology> result = new ArrayList<>();
        if (ts != null) {
            for (Technology t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Technology loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Technology t, Technology loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Technology findByReferenceEntity(Technology t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Technology> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Technology>> getToBeSavedAndToBeDeleted(List<Technology> oldList, List<Technology> newList) {
        List<List<Technology>> result = new ArrayList<>();
        List<Technology> resultDelete = new ArrayList<>();
        List<Technology> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Technology> oldList, List<Technology> newList, List<Technology> resultUpdateOrSave, List<Technology> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Technology myOld = oldList.get(i);
                Technology t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Technology myNew = newList.get(i);
                Technology t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TechnologyCollaboratorServiceImpl(TechnologyDao dao) {
        this.dao = dao;
    }

    private TechnologyDao dao;
}
