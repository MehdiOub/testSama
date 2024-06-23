package  ma.zs.zyn.dao.specification.core.template;

import ma.zs.zyn.dao.criteria.core.template.TemplateCriteria;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class TemplateSpecification extends  AbstractSpecification<TemplateCriteria, Template>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("addingDate", criteria.getAddingDate(), criteria.getAddingDateFrom(), criteria.getAddingDateTo());
        addPredicate("lastUpdateDate", criteria.getLastUpdateDate(), criteria.getLastUpdateDateFrom(), criteria.getLastUpdateDateTo());
        addPredicate("templateTags", criteria.getTemplateTags(),criteria.getTemplateTagsLike());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicateFk("technology","id", criteria.getTechnology()==null?null:criteria.getTechnology().getId());
        addPredicateFk("technology","id", criteria.getTechnologys());
        addPredicateFk("technology","code", criteria.getTechnology()==null?null:criteria.getTechnology().getCode());
    }

    public TemplateSpecification(TemplateCriteria criteria) {
        super(criteria);
    }

    public TemplateSpecification(TemplateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
