package ma.zs.zyn.service.impl.collaborator.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.TemplateDao;
import ma.zs.zyn.dao.specification.core.template.TemplateSpecification;
import ma.zs.zyn.service.facade.collaborator.template.TemplateCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.template.TechnologyCollaboratorService ;
import ma.zs.zyn.bean.core.template.Technology ;

import java.util.List;
@Service
public class TemplateCollaboratorServiceImpl implements TemplateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Template update(Template t) {
        Template loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Template.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Template findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Template findOrSave(Template t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Template result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Template> findAll() {
        return dao.findAll();
    }

    public List<Template> findByCriteria(TemplateCriteria criteria) {
        List<Template> content = null;
        if (criteria != null) {
            TemplateSpecification mySpecification = constructSpecification(criteria);
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


    private TemplateSpecification constructSpecification(TemplateCriteria criteria) {
        TemplateSpecification mySpecification =  (TemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(TemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<Template> findPaginatedByCriteria(TemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        TemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TemplateCriteria criteria) {
        TemplateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Template> findByTechnologyId(Long id){
        return dao.findByTechnologyId(id);
    }
    public int deleteByTechnologyId(Long id){
        return dao.deleteByTechnologyId(id);
    }
    public long countByTechnologyCode(String code){
        return dao.countByTechnologyCode(code);
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
    public List<Template> delete(List<Template> list) {
		List<Template> result = new ArrayList();
        if (list != null) {
            for (Template t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Template create(Template t) {
        Template loaded = findByReferenceEntity(t);
        Template saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Template findWithAssociatedLists(Long id){
        Template result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Template> update(List<Template> ts, boolean createIfNotExist) {
        List<Template> result = new ArrayList<>();
        if (ts != null) {
            for (Template t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Template loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Template t, Template loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Template findByReferenceEntity(Template t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Template t){
        if( t != null) {
        }
    }



    public List<Template> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Template>> getToBeSavedAndToBeDeleted(List<Template> oldList, List<Template> newList) {
        List<List<Template>> result = new ArrayList<>();
        List<Template> resultDelete = new ArrayList<>();
        List<Template> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Template> oldList, List<Template> newList, List<Template> resultUpdateOrSave, List<Template> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Template myOld = oldList.get(i);
                Template t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Template myNew = newList.get(i);
                Template t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private TechnologyCollaboratorService technologyService ;

    public TemplateCollaboratorServiceImpl(TemplateDao dao) {
        this.dao = dao;
    }

    private TemplateDao dao;
}
