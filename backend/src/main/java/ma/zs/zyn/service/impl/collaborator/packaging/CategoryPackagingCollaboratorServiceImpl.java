package ma.zs.zyn.service.impl.collaborator.packaging;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.dao.criteria.core.packaging.CategoryPackagingCriteria;
import ma.zs.zyn.dao.facade.core.packaging.CategoryPackagingDao;
import ma.zs.zyn.dao.specification.core.packaging.CategoryPackagingSpecification;
import ma.zs.zyn.service.facade.collaborator.packaging.CategoryPackagingCollaboratorService;
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
public class CategoryPackagingCollaboratorServiceImpl implements CategoryPackagingCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryPackaging update(CategoryPackaging t) {
        CategoryPackaging loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategoryPackaging.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategoryPackaging findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategoryPackaging findOrSave(CategoryPackaging t) {
        if (t != null) {
            CategoryPackaging result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CategoryPackaging> findAll() {
        return dao.findAll();
    }

    public List<CategoryPackaging> findByCriteria(CategoryPackagingCriteria criteria) {
        List<CategoryPackaging> content = null;
        if (criteria != null) {
            CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
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


    private CategoryPackagingSpecification constructSpecification(CategoryPackagingCriteria criteria) {
        CategoryPackagingSpecification mySpecification =  (CategoryPackagingSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategoryPackagingSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategoryPackaging> findPaginatedByCriteria(CategoryPackagingCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategoryPackagingCriteria criteria) {
        CategoryPackagingSpecification mySpecification = constructSpecification(criteria);
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
    public List<CategoryPackaging> delete(List<CategoryPackaging> list) {
		List<CategoryPackaging> result = new ArrayList();
        if (list != null) {
            for (CategoryPackaging t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategoryPackaging create(CategoryPackaging t) {
        CategoryPackaging loaded = findByReferenceEntity(t);
        CategoryPackaging saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CategoryPackaging findWithAssociatedLists(Long id){
        CategoryPackaging result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategoryPackaging> update(List<CategoryPackaging> ts, boolean createIfNotExist) {
        List<CategoryPackaging> result = new ArrayList<>();
        if (ts != null) {
            for (CategoryPackaging t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategoryPackaging loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CategoryPackaging t, CategoryPackaging loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CategoryPackaging findByReferenceEntity(CategoryPackaging t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CategoryPackaging> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategoryPackaging>> getToBeSavedAndToBeDeleted(List<CategoryPackaging> oldList, List<CategoryPackaging> newList) {
        List<List<CategoryPackaging>> result = new ArrayList<>();
        List<CategoryPackaging> resultDelete = new ArrayList<>();
        List<CategoryPackaging> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CategoryPackaging> oldList, List<CategoryPackaging> newList, List<CategoryPackaging> resultUpdateOrSave, List<CategoryPackaging> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CategoryPackaging myOld = oldList.get(i);
                CategoryPackaging t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CategoryPackaging myNew = newList.get(i);
                CategoryPackaging t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CategoryPackagingCollaboratorServiceImpl(CategoryPackagingDao dao) {
        this.dao = dao;
    }

    private CategoryPackagingDao dao;
}
