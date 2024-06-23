package  ma.zs.zyn.ws.dto.collaborator;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.zyn.ws.dto.packaging.PackagingDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InscriptionCollaboratorDto  extends AuditBaseDto {

    private String reference  ;
    private String startDate ;
    private String endDate ;
    private String renewDate ;
    private BigDecimal consumedEntity  ;
    private BigDecimal consumedProjet  ;
    private BigDecimal consumedAttribut  ;
    private BigDecimal consumedIndicator  ;

    private PackagingDto packaging ;
    private CollaboratorDto collaborator ;
    private InscriptionCollaboratorStateDto inscriptionCollaboratorState ;



    public InscriptionCollaboratorDto(){
        super();
    }



    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getRenewDate(){
        return this.renewDate;
    }
    public void setRenewDate(String renewDate){
        this.renewDate = renewDate;
    }

    @Log
    public BigDecimal getConsumedEntity(){
        return this.consumedEntity;
    }
    public void setConsumedEntity(BigDecimal consumedEntity){
        this.consumedEntity = consumedEntity;
    }

    @Log
    public BigDecimal getConsumedProjet(){
        return this.consumedProjet;
    }
    public void setConsumedProjet(BigDecimal consumedProjet){
        this.consumedProjet = consumedProjet;
    }

    @Log
    public BigDecimal getConsumedAttribut(){
        return this.consumedAttribut;
    }
    public void setConsumedAttribut(BigDecimal consumedAttribut){
        this.consumedAttribut = consumedAttribut;
    }

    @Log
    public BigDecimal getConsumedIndicator(){
        return this.consumedIndicator;
    }
    public void setConsumedIndicator(BigDecimal consumedIndicator){
        this.consumedIndicator = consumedIndicator;
    }


    public PackagingDto getPackaging(){
        return this.packaging;
    }

    public void setPackaging(PackagingDto packaging){
        this.packaging = packaging;
    }
    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public InscriptionCollaboratorStateDto getInscriptionCollaboratorState(){
        return this.inscriptionCollaboratorState;
    }

    public void setInscriptionCollaboratorState(InscriptionCollaboratorStateDto inscriptionCollaboratorState){
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }






}
