package ma.zs.zyn.service.facade.collaborator.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface TemplateCollaboratorService {



    List<Template> findByTechnologyId(Long id);
    int deleteByTechnologyId(Long id);
    long countByTechnologyCode(String code);




	Template create(Template t);

    Template update(Template t);

    List<Template> update(List<Template> ts,boolean createIfNotExist);

    Template findById(Long id);

    Template findOrSave(Template t);

    Template findByReferenceEntity(Template t);

    Template findWithAssociatedLists(Long id);

    List<Template> findAllOptimized();

    List<Template> findAll();

    List<Template> findByCriteria(TemplateCriteria criteria);

    List<Template> findPaginatedByCriteria(TemplateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TemplateCriteria criteria);

    List<Template> delete(List<Template> ts);

    boolean deleteById(Long id);

    List<List<Template>> getToBeSavedAndToBeDeleted(List<Template> oldList, List<Template> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
