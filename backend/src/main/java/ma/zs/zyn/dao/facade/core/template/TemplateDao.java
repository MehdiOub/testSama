package ma.zs.zyn.dao.facade.core.template;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.template.Template;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.template.Template;
import java.util.List;


@Repository
public interface TemplateDao extends AbstractRepository<Template,Long>  {
    Template findByCode(String code);
    int deleteByCode(String code);

    List<Template> findByTechnologyId(Long id);
    int deleteByTechnologyId(Long id);
    long countByTechnologyCode(String code);

    @Query("SELECT NEW Template(item.id,item.libelle) FROM Template item")
    List<Template> findAllOptimized();

}
