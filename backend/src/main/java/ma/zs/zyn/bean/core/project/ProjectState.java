package ma.zs.zyn.bean.core.project;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_state_seq",sequenceName="project_state_seq",allocationSize=1, initialValue = 1)
public class ProjectState  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public ProjectState(){
        super();
    }

    public ProjectState(Long id){
        this.id = id;
    }

    public ProjectState(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ProjectState(String libelle){
        this.libelle = libelle ;
    }
    public ProjectState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_state_seq")
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

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectState projectState = (ProjectState) o;
        return id != null && id.equals(projectState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

