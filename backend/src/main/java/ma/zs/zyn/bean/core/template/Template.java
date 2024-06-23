package ma.zs.zyn.bean.core.template;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "template")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="template_seq",sequenceName="template_seq",allocationSize=1, initialValue = 1)
public class Template  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String description;

    private LocalDateTime addingDate ;

    private LocalDateTime lastUpdateDate ;

    @Column(length = 500)
    private String templateTags;

    private BigDecimal price = BigDecimal.ZERO;

    private Technology technology ;


    public Template(){
        super();
    }

    public Template(Long id){
        this.id = id;
    }

    public Template(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Template(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="template_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getAddingDate(){
        return this.addingDate;
    }
    public void setAddingDate(LocalDateTime addingDate){
        this.addingDate = addingDate;
    }
    public LocalDateTime getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(LocalDateTime lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
    }
    public String getTemplateTags(){
        return this.templateTags;
    }
    public void setTemplateTags(String templateTags){
        this.templateTags = templateTags;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology")
    public Technology getTechnology(){
        return this.technology;
    }
    public void setTechnology(Technology technology){
        this.technology = technology;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return id != null && id.equals(template.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

