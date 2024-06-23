package ma.zs.zyn.dao.facade.core.packaging;

import org.springframework.data.jpa.repository.Query;
import ma.zs.zyn.zynerator.repository.AbstractRepository;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import org.springframework.stereotype.Repository;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import java.util.List;


@Repository
public interface CategoryPackagingDao extends AbstractRepository<CategoryPackaging,Long>  {
    CategoryPackaging findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategoryPackaging(item.id,item.libelle) FROM CategoryPackaging item")
    List<CategoryPackaging> findAllOptimized();

}
