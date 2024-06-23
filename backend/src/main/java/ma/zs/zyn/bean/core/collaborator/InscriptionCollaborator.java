package ma.zs.zyn.bean.core.collaborator;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.packaging.Packaging;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "inscription_collaborator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="inscription_collaborator_seq",sequenceName="inscription_collaborator_seq",allocationSize=1, initialValue = 1)
public class InscriptionCollaborator  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String reference;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    private LocalDateTime renewDate ;

    private BigDecimal consumedEntity = BigDecimal.ZERO;

    private BigDecimal consumedProjet = BigDecimal.ZERO;

    private BigDecimal consumedAttribut = BigDecimal.ZERO;

    private BigDecimal consumedIndicator = BigDecimal.ZERO;

    private Packaging packaging ;
    private Collaborator collaborator ;
    private InscriptionCollaboratorState inscriptionCollaboratorState ;


    public InscriptionCollaborator(){
        super();
    }

    public InscriptionCollaborator(Long id){
        this.id = id;
    }

    public InscriptionCollaborator(Long id,String reference){
        this.id = id;
        this.reference = reference ;
    }
    public InscriptionCollaborator(String reference){
        this.reference = reference ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="inscription_collaborator_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getRenewDate(){
        return this.renewDate;
    }
    public void setRenewDate(LocalDateTime renewDate){
        this.renewDate = renewDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging")
    public Packaging getPackaging(){
        return this.packaging;
    }
    public void setPackaging(Packaging packaging){
        this.packaging = packaging;
    }
    public BigDecimal getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(BigDecimal consumedEntity){
        this.consumedEntity = consumedEntity;
    }
    public BigDecimal getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(BigDecimal consumedProjet){
        this.consumedProjet = consumedProjet;
    }
    public BigDecimal getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(BigDecimal consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }
    public BigDecimal getConsumedIndicator(){
        return this.consumedIndicator;
    }
    public void setConsumedIndicator(BigDecimal consumedIndicator){
        this.consumedIndicator = consumedIndicator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator")
    public Collaborator getCollaborator(){
        return this.collaborator;
    }
    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_collaborator_state")
    public InscriptionCollaboratorState getInscriptionCollaboratorState(){
        return this.inscriptionCollaboratorState;
    }
    public void setInscriptionCollaboratorState(InscriptionCollaboratorState inscriptionCollaboratorState){
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }

    @Transient
    public String getLabel() {
        label = reference;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscriptionCollaborator inscriptionCollaborator = (InscriptionCollaborator) o;
        return id != null && id.equals(inscriptionCollaborator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

