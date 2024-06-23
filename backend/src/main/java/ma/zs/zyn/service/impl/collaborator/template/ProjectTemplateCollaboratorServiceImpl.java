package ma.zs.zyn.service.impl.collaborator.template;


import ma.zs.zyn.zynerator.exception.EntityNotFoundException;
import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.dao.criteria.core.template.ProjectTemplateCriteria;
import ma.zs.zyn.dao.facade.core.template.ProjectTemplateDao;
import ma.zs.zyn.dao.specification.core.template.ProjectTemplateSpecification;
import ma.zs.zyn.service.facade.collaborator.template.ProjectTemplateCollaboratorService;
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

import ma.zs.zyn.service.facade.collaborator.project.ProjectCollaboratorService ;
import ma.zs.zyn.bean.core.project.Project ;
import ma.zs.zyn.service.facade.collaborator.template.TemplateCollaboratorService ;
import ma.zs.zyn.bean.core.template.Template ;

import java.util.List;
@Service
public class ProjectTemplateCollaboratorServiceImpl implements ProjectTemplateCollaboratorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTemplate update(ProjectTemplate t) {
        ProjectTemplate loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjectTemplate.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjectTemplate findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjectTemplate findOrSave(ProjectTemplate t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjectTemplate result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProjectTemplate> findAll() {
        return dao.findAll();
    }

    public List<ProjectTemplate> findByCriteria(ProjectTemplateCriteria criteria) {
        List<ProjectTemplate> content = null;
        if (criteria != null) {
            ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
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


    private ProjectTemplateSpecification constructSpecification(ProjectTemplateCriteria criteria) {
        ProjectTemplateSpecification mySpecification =  (ProjectTemplateSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjectTemplateSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjectTemplate> findPaginatedByCriteria(ProjectTemplateCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjectTemplateCriteria criteria) {
        ProjectTemplateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjectTemplate> findByTemplateId(Long id){
        return dao.findByTemplateId(id);
    }
    public int deleteByTemplateId(Long id){
        return dao.deleteByTemplateId(id);
    }
    public long countByTemplateCode(String code){
        return dao.countByTemplateCode(code);
    }
    public List<ProjectTemplate> findByProjectId(Long id){
        return dao.findByProjectId(id);
    }
    public int deleteByProjectId(Long id){
        return dao.deleteByProjectId(id);
    }
    public long countByProjectCode(String code){
        return dao.countByProjectCode(code);
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
    public List<ProjectTemplate> delete(List<ProjectTemplate> list) {
		List<ProjectTemplate> result = new ArrayList();
        if (list != null) {
            for (ProjectTemplate t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjectTemplate create(ProjectTemplate t) {
        ProjectTemplate loaded = findByReferenceEntity(t);
        ProjectTemplate saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProjectTemplate findWithAssociatedLists(Long id){
        ProjectTemplate result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjectTemplate> update(List<ProjectTemplate> ts, boolean createIfNotExist) {
        List<ProjectTemplate> result = new ArrayList<>();
        if (ts != null) {
            for (ProjectTemplate t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjectTemplate loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProjectTemplate t, ProjectTemplate loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProjectTemplate findByReferenceEntity(ProjectTemplate t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjectTemplate t){
        if( t != null) {
        }
    }



    public List<ProjectTemplate> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProjectTemplate>> getToBeSavedAndToBeDeleted(List<ProjectTemplate> oldList, List<ProjectTemplate> newList) {
        List<List<ProjectTemplate>> result = new ArrayList<>();
        List<ProjectTemplate> resultDelete = new ArrayList<>();
        List<ProjectTemplate> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProjectTemplate> oldList, List<ProjectTemplate> newList, List<ProjectTemplate> resultUpdateOrSave, List<ProjectTemplate> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProjectTemplate myOld = oldList.get(i);
                ProjectTemplate t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProjectTemplate myNew = newList.get(i);
                ProjectTemplate t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ProjectCollaboratorService projectService ;
    @Autowired
    private TemplateCollaboratorService templateService ;

    public ProjectTemplateCollaboratorServiceImpl(ProjectTemplateDao dao) {
        this.dao = dao;
    }

    private ProjectTemplateDao dao;
}
