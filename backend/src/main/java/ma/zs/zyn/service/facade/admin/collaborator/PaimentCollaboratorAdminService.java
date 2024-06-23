package ma.zs.zyn.service.facade.admin.collaborator;

import java.util.List;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.dao.criteria.core.collaborator.PaimentCollaboratorCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface PaimentCollaboratorAdminService {



    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorReference(String reference);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
    int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);




	PaimentCollaborator create(PaimentCollaborator t);

    PaimentCollaborator update(PaimentCollaborator t);

    List<PaimentCollaborator> update(List<PaimentCollaborator> ts,boolean createIfNotExist);

    PaimentCollaborator findById(Long id);

    PaimentCollaborator findOrSave(PaimentCollaborator t);

    PaimentCollaborator findByReferenceEntity(PaimentCollaborator t);

    PaimentCollaborator findWithAssociatedLists(Long id);

    List<PaimentCollaborator> findAllOptimized();

    List<PaimentCollaborator> findAll();

    List<PaimentCollaborator> findByCriteria(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> findPaginatedByCriteria(PaimentCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaimentCollaboratorCriteria criteria);

    List<PaimentCollaborator> delete(List<PaimentCollaborator> ts);

    boolean deleteById(Long id);

    List<List<PaimentCollaborator>> getToBeSavedAndToBeDeleted(List<PaimentCollaborator> oldList, List<PaimentCollaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
