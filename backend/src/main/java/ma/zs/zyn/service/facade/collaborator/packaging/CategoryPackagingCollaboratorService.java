package ma.zs.zyn.service.facade.collaborator.packaging;

import java.util.List;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.dao.criteria.core.packaging.CategoryPackagingCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface CategoryPackagingCollaboratorService {







	CategoryPackaging create(CategoryPackaging t);

    CategoryPackaging update(CategoryPackaging t);

    List<CategoryPackaging> update(List<CategoryPackaging> ts,boolean createIfNotExist);

    CategoryPackaging findById(Long id);

    CategoryPackaging findOrSave(CategoryPackaging t);

    CategoryPackaging findByReferenceEntity(CategoryPackaging t);

    CategoryPackaging findWithAssociatedLists(Long id);

    List<CategoryPackaging> findAllOptimized();

    List<CategoryPackaging> findAll();

    List<CategoryPackaging> findByCriteria(CategoryPackagingCriteria criteria);

    List<CategoryPackaging> findPaginatedByCriteria(CategoryPackagingCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategoryPackagingCriteria criteria);

    List<CategoryPackaging> delete(List<CategoryPackaging> ts);

    boolean deleteById(Long id);

    List<List<CategoryPackaging>> getToBeSavedAndToBeDeleted(List<CategoryPackaging> oldList, List<CategoryPackaging> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
