package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class TechnologySpecification extends  AbstractSpecification<TechnologyCriteria, Technology>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("logo", criteria.getLogo(),criteria.getLogoLike());
    }

    public TechnologySpecification(TechnologyCriteria criteria) {
        super(criteria);
    }

    public TechnologySpecification(TechnologyCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
