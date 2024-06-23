package  ma.zs.zyn.ws.dto.template;

import ma.zs.zyn.zynerator.audit.Log;
import ma.zs.zyn.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String addingDate ;
    private String lastUpdateDate ;
    private String templateTags  ;
    private BigDecimal price  ;

    private TechnologyDto technology ;



    public TemplateDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getAddingDate(){
        return this.addingDate;
    }
    public void setAddingDate(String addingDate){
        this.addingDate = addingDate;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(String lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
    }

    @Log
    public String getTemplateTags(){
        return this.templateTags;
    }
    public void setTemplateTags(String templateTags){
        this.templateTags = templateTags;
    }

    @Log
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public TechnologyDto getTechnology(){
        return this.technology;
    }

    public void setTechnology(TechnologyDto technology){
        this.technology = technology;
    }






}
