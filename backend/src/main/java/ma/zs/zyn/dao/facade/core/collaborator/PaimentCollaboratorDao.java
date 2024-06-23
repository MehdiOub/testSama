package ma.zs.zyn.dao.facade.core.collaborator;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import java.util.List;


@Repository
public interface PaimentCollaboratorDao extends AbstractRepository<PaimentCollaborator,Long>  {
    PaimentCollaborator findByCode(String code);
    int deleteByCode(String code);

    List<PaimentCollaborator> findByInscriptionCollaboratorId(Long id);
    int deleteByInscriptionCollaboratorId(Long id);
    long countByInscriptionCollaboratorReference(String reference);
    List<PaimentCollaborator> findByPaimentCollaboratorStateCode(String code);
            public int deleteByPaimentCollaboratorStateCode(String code);
    long countByPaimentCollaboratorStateCode(String code);

    @Query("SELECT NEW PaimentCollaborator(item.id,item.libelle) FROM PaimentCollaborator item")
    List<PaimentCollaborator> findAllOptimized();

}
