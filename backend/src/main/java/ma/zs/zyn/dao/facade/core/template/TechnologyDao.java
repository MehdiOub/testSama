package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.Technology;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.template.Technology;
import java.util.List;


@Repository
public interface TechnologyDao extends AbstractRepository<Technology,Long>  {
    Technology findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Technology(item.id,item.libelle) FROM Technology item")
    List<Technology> findAllOptimized();

}
