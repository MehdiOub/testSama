package ma.zs.zyn.service.facade.collaborator.collaborator;

import java.util.List;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState;
import ma.zs.zyn.dao.criteria.core.collaborator.InscriptionCollaboratorStateCriteria;
import ma.zs.zyn.zynerator.service.IService;



public interface InscriptionCollaboratorStateCollaboratorService {







	InscriptionCollaboratorState create(InscriptionCollaboratorState t);

    InscriptionCollaboratorState update(InscriptionCollaboratorState t);

    List<InscriptionCollaboratorState> update(List<InscriptionCollaboratorState> ts,boolean createIfNotExist);

    InscriptionCollaboratorState findById(Long id);

    InscriptionCollaboratorState findOrSave(InscriptionCollaboratorState t);

    InscriptionCollaboratorState findByReferenceEntity(InscriptionCollaboratorState t);

    InscriptionCollaboratorState findWithAssociatedLists(Long id);

    List<InscriptionCollaboratorState> findAllOptimized();

    List<InscriptionCollaboratorState> findAll();

    List<InscriptionCollaboratorState> findByCriteria(InscriptionCollaboratorStateCriteria criteria);

    List<InscriptionCollaboratorState> findPaginatedByCriteria(InscriptionCollaboratorStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCollaboratorStateCriteria criteria);

    List<InscriptionCollaboratorState> delete(List<InscriptionCollaboratorState> ts);

    boolean deleteById(Long id);

    List<List<InscriptionCollaboratorState>> getToBeSavedAndToBeDeleted(List<InscriptionCollaboratorState> oldList, List<InscriptionCollaboratorState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
