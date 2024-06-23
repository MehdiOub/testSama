package ma.zs.zyn.dao.facade.core.collaborator;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import java.util.List;


@Repository
public interface InscriptionCollaboratorDao extends AbstractRepository<InscriptionCollaborator,Long>  {
    InscriptionCollaborator findByReference(String reference);
    int deleteByReference(String reference);

    List<InscriptionCollaborator> findByPackagingId(Long id);
    int deleteByPackagingId(Long id);
    long countByPackagingCode(String code);
    List<InscriptionCollaborator> findByCollaboratorId(Long id);
    int deleteByCollaboratorId(Long id);
    long countByCollaboratorId(Long id);
    List<InscriptionCollaborator> findByInscriptionCollaboratorStateCode(String code);
            public int deleteByInscriptionCollaboratorStateCode(String code);
    long countByInscriptionCollaboratorStateCode(String code);

    @Query("SELECT NEW InscriptionCollaborator(item.id,item.reference) FROM InscriptionCollaborator item")
    List<InscriptionCollaborator> findAllOptimized();

}
