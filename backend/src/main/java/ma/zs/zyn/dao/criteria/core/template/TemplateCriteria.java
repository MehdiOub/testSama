package  ma.zs.zyn.dao.criteria.core.template;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class TemplateCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime addingDate;
    private LocalDateTime addingDateFrom;
    private LocalDateTime addingDateTo;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime lastUpdateDateFrom;
    private LocalDateTime lastUpdateDateTo;
    private String templateTags;
    private String templateTagsLike;
    private String price;
    private String priceMin;
    private String priceMax;

    private TechnologyCriteria technology ;
    private List<TechnologyCriteria> technologys ;


    public TemplateCriteria(){}

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getAddingDate(){
        return this.addingDate;
    }
    public void setAddingDate(LocalDateTime addingDate){
        this.addingDate = addingDate;
    }
    public LocalDateTime getAddingDateFrom(){
        return this.addingDateFrom;
    }
    public void setAddingDateFrom(LocalDateTime addingDateFrom){
        this.addingDateFrom = addingDateFrom;
    }
    public LocalDateTime getAddingDateTo(){
        return this.addingDateTo;
    }
    public void setAddingDateTo(LocalDateTime addingDateTo){
        this.addingDateTo = addingDateTo;
    }
    public LocalDateTime getLastUpdateDate(){
        return this.lastUpdateDate;
    }
    public void setLastUpdateDate(LocalDateTime lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
    }
    public LocalDateTime getLastUpdateDateFrom(){
        return this.lastUpdateDateFrom;
    }
    public void setLastUpdateDateFrom(LocalDateTime lastUpdateDateFrom){
        this.lastUpdateDateFrom = lastUpdateDateFrom;
    }
    public LocalDateTime getLastUpdateDateTo(){
        return this.lastUpdateDateTo;
    }
    public void setLastUpdateDateTo(LocalDateTime lastUpdateDateTo){
        this.lastUpdateDateTo = lastUpdateDateTo;
    }
    public String getTemplateTags(){
        return this.templateTags;
    }
    public void setTemplateTags(String templateTags){
        this.templateTags = templateTags;
    }
    public String getTemplateTagsLike(){
        return this.templateTagsLike;
    }
    public void setTemplateTagsLike(String templateTagsLike){
        this.templateTagsLike = templateTagsLike;
    }

    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }   
    public String getPriceMin(){
        return this.priceMin;
    }
    public void setPriceMin(String priceMin){
        this.priceMin = priceMin;
    }
    public String getPriceMax(){
        return this.priceMax;
    }
    public void setPriceMax(String priceMax){
        this.priceMax = priceMax;
    }
      

    public TechnologyCriteria getTechnology(){
        return this.technology;
    }

    public void setTechnology(TechnologyCriteria technology){
        this.technology = technology;
    }
    public List<TechnologyCriteria> getTechnologys(){
        return this.technologys;
    }

    public void setTechnologys(List<TechnologyCriteria> technologys){
        this.technologys = technologys;
    }
}
