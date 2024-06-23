package  ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.dao.criteria.core.collaborator.InscriptionCollaboratorCriteria;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class InscriptionCollaboratorSpecification extends  AbstractSpecification<InscriptionCollaboratorCriteria, InscriptionCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicate("renewDate", criteria.getRenewDate(), criteria.getRenewDateFrom(), criteria.getRenewDateTo());
        addPredicateBigDecimal("consumedEntity", criteria.getConsumedEntity(), criteria.getConsumedEntityMin(), criteria.getConsumedEntityMax());
        addPredicateBigDecimal("consumedProjet", criteria.getConsumedProjet(), criteria.getConsumedProjetMin(), criteria.getConsumedProjetMax());
        addPredicateBigDecimal("consumedAttribut", criteria.getConsumedAttribut(), criteria.getConsumedAttributMin(), criteria.getConsumedAttributMax());
        addPredicateBigDecimal("consumedIndicator", criteria.getConsumedIndicator(), criteria.getConsumedIndicatorMin(), criteria.getConsumedIndicatorMax());
        addPredicateFk("packaging","id", criteria.getPackaging()==null?null:criteria.getPackaging().getId());
        addPredicateFk("packaging","id", criteria.getPackagings());
        addPredicateFk("packaging","code", criteria.getPackaging()==null?null:criteria.getPackaging().getCode());
        addPredicateFk("collaborator","id", criteria.getCollaborator()==null?null:criteria.getCollaborator().getId());
        addPredicateFk("collaborator","id", criteria.getCollaborators());
        addPredicateFk("inscriptionCollaboratorState","id", criteria.getInscriptionCollaboratorState()==null?null:criteria.getInscriptionCollaboratorState().getId());
        addPredicateFk("inscriptionCollaboratorState","id", criteria.getInscriptionCollaboratorStates());
        addPredicateFk("inscriptionCollaboratorState","code", criteria.getInscriptionCollaboratorState()==null?null:criteria.getInscriptionCollaboratorState().getCode());
    }

    public InscriptionCollaboratorSpecification(InscriptionCollaboratorCriteria criteria) {
        super(criteria);
    }

    public InscriptionCollaboratorSpecification(InscriptionCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
