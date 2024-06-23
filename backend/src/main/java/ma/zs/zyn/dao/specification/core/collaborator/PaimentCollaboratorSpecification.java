package  ma.zs.zyn.dao.specification.core.collaborator;

import ma.zs.zyn.dao.criteria.core.collaborator.PaimentCollaboratorCriteria;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.zynerator.specification.AbstractSpecification;


public class PaimentCollaboratorSpecification extends  AbstractSpecification<PaimentCollaboratorCriteria, PaimentCollaborator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateBigDecimal("amountToPaid", criteria.getAmountToPaid(), criteria.getAmountToPaidMin(), criteria.getAmountToPaidMax());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("discount", criteria.getDiscount(), criteria.getDiscountMin(), criteria.getDiscountMax());
        addPredicateBigDecimal("remaining", criteria.getRemaining(), criteria.getRemainingMin(), criteria.getRemainingMax());
        addPredicate("paiementDate", criteria.getPaiementDate(), criteria.getPaiementDateFrom(), criteria.getPaiementDateTo());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborator()==null?null:criteria.getInscriptionCollaborator().getId());
        addPredicateFk("inscriptionCollaborator","id", criteria.getInscriptionCollaborators());
        addPredicateFk("inscriptionCollaborator","reference", criteria.getInscriptionCollaborator()==null?null:criteria.getInscriptionCollaborator().getReference());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getId());
        addPredicateFk("paimentCollaboratorState","id", criteria.getPaimentCollaboratorStates());
        addPredicateFk("paimentCollaboratorState","code", criteria.getPaimentCollaboratorState()==null?null:criteria.getPaimentCollaboratorState().getCode());
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria) {
        super(criteria);
    }

    public PaimentCollaboratorSpecification(PaimentCollaboratorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
