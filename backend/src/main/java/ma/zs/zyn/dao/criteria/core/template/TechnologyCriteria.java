package  ma.zs.zyn.dao.criteria.core.template;



import ma.zs.zyn.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TechnologyCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String logo;
    private String logoLike;



    public TechnologyCriteria(){}

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

    public String getLogo(){
        return this.logo;
    }
    public void setLogo(String logo){
        this.logo = logo;
    }
    public String getLogoLike(){
        return this.logoLike;
    }
    public void setLogoLike(String logoLike){
        this.logoLike = logoLike;
    }


}
