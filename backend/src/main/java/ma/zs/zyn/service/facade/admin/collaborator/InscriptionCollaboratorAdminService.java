package ma.zs.zyn.service.facade.admin.collaborator;

import java.util.List;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.InscriptionCollaboratorCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface InscriptionCollaboratorAdminService {



    List<InscriptionCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);
    List<InscriptionCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByInscriptionCollaboratorStateCode(String code);
    int deleteByInscriptionCollaboratorStateCode(String code);
    long countByInscriptionCollaboratorStateCode(String code);




	InscriptionCollaborator create(InscriptionCollaborator t);

    InscriptionCollaborator update(InscriptionCollaborator t);

    List<InscriptionCollaborator> update(List<InscriptionCollaborator> ts,boolean createIfNotExist);

    InscriptionCollaborator findById(Long id);

    InscriptionCollaborator findOrSave(InscriptionCollaborator t);

    InscriptionCollaborator findByReferenceEntity(InscriptionCollaborator t);

    InscriptionCollaborator findWithAssociatedLists(Long id);

    List<InscriptionCollaborator> findAllOptimized();

    List<InscriptionCollaborator> findAll();

    List<InscriptionCollaborator> findByCriteria(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> findPaginatedByCriteria(InscriptionCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCollaboratorCriteria criteria);

    List<InscriptionCollaborator> delete(List<InscriptionCollaborator> ts);

    boolean deleteById(Long id);

    List<List<InscriptionCollaborator>> getToBeSavedAndToBeDeleted(List<InscriptionCollaborator> oldList, List<InscriptionCollaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
