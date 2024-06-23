package  ma.zs.zyn.ws.converter.collaborator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.collaborator.CollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.ws.converter.collaborator.InscriptionCollaboratorStateConverter;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import ma.zs.zyn.ws.dto.collaborator.InscriptionCollaboratorDto;

@Component
public class InscriptionCollaboratorConverter {

    @Autowired
    private CollaboratorConverter collaboratorConverter ;
    @Autowired
    private PackagingConverter packagingConverter ;
    @Autowired
    private InscriptionCollaboratorStateConverter inscriptionCollaboratorStateConverter ;
    private boolean packaging;
    private boolean collaborator;
    private boolean inscriptionCollaboratorState;

    public  InscriptionCollaboratorConverter() {
        initObject(true);
    }


    public InscriptionCollaborator toItem(InscriptionCollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        InscriptionCollaborator item = new InscriptionCollaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getRenewDate()))
                item.setRenewDate(DateUtil.stringEnToDate(dto.getRenewDate()));
            if(StringUtil.isNotEmpty(dto.getConsumedEntity()))
                item.setConsumedEntity(dto.getConsumedEntity());
            if(StringUtil.isNotEmpty(dto.getConsumedProjet()))
                item.setConsumedProjet(dto.getConsumedProjet());
            if(StringUtil.isNotEmpty(dto.getConsumedAttribut()))
                item.setConsumedAttribut(dto.getConsumedAttribut());
            if(StringUtil.isNotEmpty(dto.getConsumedIndicator()))
                item.setConsumedIndicator(dto.getConsumedIndicator());
            if(this.packaging && dto.getPackaging()!=null)
                item.setPackaging(packagingConverter.toItem(dto.getPackaging())) ;

            if(this.collaborator && dto.getCollaborator()!=null)
                item.setCollaborator(collaboratorConverter.toItem(dto.getCollaborator())) ;

            if(this.inscriptionCollaboratorState && dto.getInscriptionCollaboratorState()!=null)
                item.setInscriptionCollaboratorState(inscriptionCollaboratorStateConverter.toItem(dto.getInscriptionCollaboratorState())) ;




        return item;
        }
    }


    public InscriptionCollaboratorDto toDto(InscriptionCollaborator item) {
        if (item == null) {
            return null;
        } else {
            InscriptionCollaboratorDto dto = new InscriptionCollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(item.getRenewDate()!=null)
                dto.setRenewDate(DateUtil.dateTimeToString(item.getRenewDate()));
            if(StringUtil.isNotEmpty(item.getConsumedEntity()))
                dto.setConsumedEntity(item.getConsumedEntity());
            if(StringUtil.isNotEmpty(item.getConsumedProjet()))
                dto.setConsumedProjet(item.getConsumedProjet());
            if(StringUtil.isNotEmpty(item.getConsumedAttribut()))
                dto.setConsumedAttribut(item.getConsumedAttribut());
            if(StringUtil.isNotEmpty(item.getConsumedIndicator()))
                dto.setConsumedIndicator(item.getConsumedIndicator());
            if(this.packaging && item.getPackaging()!=null) {
                dto.setPackaging(packagingConverter.toDto(item.getPackaging())) ;

            }
            if(this.collaborator && item.getCollaborator()!=null) {
                dto.setCollaborator(collaboratorConverter.toDto(item.getCollaborator())) ;

            }
            if(this.inscriptionCollaboratorState && item.getInscriptionCollaboratorState()!=null) {
                dto.setInscriptionCollaboratorState(inscriptionCollaboratorStateConverter.toDto(item.getInscriptionCollaboratorState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.packaging = value;
        this.collaborator = value;
        this.inscriptionCollaboratorState = value;
    }
	
    public List<InscriptionCollaborator> toItem(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InscriptionCollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InscriptionCollaboratorDto> toDto(List<InscriptionCollaborator> items) {
        List<InscriptionCollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InscriptionCollaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InscriptionCollaboratorDto dto, InscriptionCollaborator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPackaging() == null  && dto.getPackaging() != null){
            t.setPackaging(new Packaging());
        }
        if(t.getCollaborator() == null  && dto.getCollaborator() != null){
            t.setCollaborator(new Collaborator());
        }
        if(t.getInscriptionCollaboratorState() == null  && dto.getInscriptionCollaboratorState() != null){
            t.setInscriptionCollaboratorState(new InscriptionCollaboratorState());
        }
        if (dto.getPackaging() != null)
        packagingConverter.copy(dto.getPackaging(), t.getPackaging());
        if (dto.getCollaborator() != null)
        collaboratorConverter.copy(dto.getCollaborator(), t.getCollaborator());
        if (dto.getInscriptionCollaboratorState() != null)
        inscriptionCollaboratorStateConverter.copy(dto.getInscriptionCollaboratorState(), t.getInscriptionCollaboratorState());
    }

    public List<InscriptionCollaborator> copy(List<InscriptionCollaboratorDto> dtos) {
        List<InscriptionCollaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (InscriptionCollaboratorDto dto : dtos) {
                InscriptionCollaborator instance = new InscriptionCollaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CollaboratorConverter getCollaboratorConverter(){
        return this.collaboratorConverter;
    }
    public void setCollaboratorConverter(CollaboratorConverter collaboratorConverter ){
        this.collaboratorConverter = collaboratorConverter;
    }
    public PackagingConverter getPackagingConverter(){
        return this.packagingConverter;
    }
    public void setPackagingConverter(PackagingConverter packagingConverter ){
        this.packagingConverter = packagingConverter;
    }
    public InscriptionCollaboratorStateConverter getInscriptionCollaboratorStateConverter(){
        return this.inscriptionCollaboratorStateConverter;
    }
    public void setInscriptionCollaboratorStateConverter(InscriptionCollaboratorStateConverter inscriptionCollaboratorStateConverter ){
        this.inscriptionCollaboratorStateConverter = inscriptionCollaboratorStateConverter;
    }
    public boolean  isPackaging(){
        return this.packaging;
    }
    public void  setPackaging(boolean packaging){
        this.packaging = packaging;
    }
    public boolean  isCollaborator(){
        return this.collaborator;
    }
    public void  setCollaborator(boolean collaborator){
        this.collaborator = collaborator;
    }
    public boolean  isInscriptionCollaboratorState(){
        return this.inscriptionCollaboratorState;
    }
    public void  setInscriptionCollaboratorState(boolean inscriptionCollaboratorState){
        this.inscriptionCollaboratorState = inscriptionCollaboratorState;
    }
}
