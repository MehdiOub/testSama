package  ma.zs.zyn.dao.specification.core.packaging;

import ma.zs.zyn.dao.criteria.core.packaging.CategoryPackagingCriteria;
import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class CategoryPackagingSpecification extends  AbstractSpecification<CategoryPackagingCriteria, CategoryPackaging>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CategoryPackagingSpecification(CategoryPackagingCriteria criteria) {
        super(criteria);
    }

    public CategoryPackagingSpecification(CategoryPackagingCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
