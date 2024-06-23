package ma.zs.zyn.dao.facade.core.collaborator;

import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CollaboratorDao extends AbstractRepository<Collaborator,Long>  {

    Collaborator findByUsername(String username);


}
