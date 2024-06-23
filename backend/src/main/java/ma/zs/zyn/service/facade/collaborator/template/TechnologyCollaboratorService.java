package ma.zs.zyn.service.facade.collaborator.template;

import java.util.List;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface TechnologyCollaboratorService {







	Technology create(Technology t);

    Technology update(Technology t);

    List<Technology> update(List<Technology> ts,boolean createIfNotExist);

    Technology findById(Long id);

    Technology findOrSave(Technology t);

    Technology findByReferenceEntity(Technology t);

    Technology findWithAssociatedLists(Long id);

    List<Technology> findAllOptimized();

    List<Technology> findAll();

    List<Technology> findByCriteria(TechnologyCriteria criteria);

    List<Technology> findPaginatedByCriteria(TechnologyCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TechnologyCriteria criteria);

    List<Technology> delete(List<Technology> ts);

    boolean deleteById(Long id);

    List<List<Technology>> getToBeSavedAndToBeDeleted(List<Technology> oldList, List<Technology> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
