package  ma.zs.zyn.ws.converter.collaborator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.collaborator.PaimentCollaboratorStateConverter;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState;
import ma.zs.zyn.ws.converter.collaborator.InscriptionCollaboratorConverter;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.ws.dto.collaborator.PaimentCollaboratorDto;

@Component
public class PaimentCollaboratorConverter {

    @Autowired
    private PaimentCollaboratorStateConverter paimentCollaboratorStateConverter ;
    @Autowired
    private InscriptionCollaboratorConverter inscriptionCollaboratorConverter ;
    private boolean inscriptionCollaborator;
    private boolean paimentCollaboratorState;

    public  PaimentCollaboratorConverter() {
        initObject(true);
    }


    public PaimentCollaborator toItem(PaimentCollaboratorDto dto) {
        if (dto == null) {
            return null;
        } else {
        PaimentCollaborator item = new PaimentCollaborator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getAmountToPaid()))
                item.setAmountToPaid(dto.getAmountToPaid());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getDiscount()))
                item.setDiscount(dto.getDiscount());
            if(StringUtil.isNotEmpty(dto.getRemaining()))
                item.setRemaining(dto.getRemaining());
            if(StringUtil.isNotEmpty(dto.getPaiementDate()))
                item.setPaiementDate(DateUtil.stringEnToDate(dto.getPaiementDate()));
            if(this.inscriptionCollaborator && dto.getInscriptionCollaborator()!=null)
                item.setInscriptionCollaborator(inscriptionCollaboratorConverter.toItem(dto.getInscriptionCollaborator())) ;

            if(this.paimentCollaboratorState && dto.getPaimentCollaboratorState()!=null)
                item.setPaimentCollaboratorState(paimentCollaboratorStateConverter.toItem(dto.getPaimentCollaboratorState())) ;




        return item;
        }
    }


    public PaimentCollaboratorDto toDto(PaimentCollaborator item) {
        if (item == null) {
            return null;
        } else {
            PaimentCollaboratorDto dto = new PaimentCollaboratorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getAmountToPaid()))
                dto.setAmountToPaid(item.getAmountToPaid());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getDiscount()))
                dto.setDiscount(item.getDiscount());
            if(StringUtil.isNotEmpty(item.getRemaining()))
                dto.setRemaining(item.getRemaining());
            if(item.getPaiementDate()!=null)
                dto.setPaiementDate(DateUtil.dateTimeToString(item.getPaiementDate()));
            if(this.inscriptionCollaborator && item.getInscriptionCollaborator()!=null) {
                dto.setInscriptionCollaborator(inscriptionCollaboratorConverter.toDto(item.getInscriptionCollaborator())) ;

            }
            if(this.paimentCollaboratorState && item.getPaimentCollaboratorState()!=null) {
                dto.setPaimentCollaboratorState(paimentCollaboratorStateConverter.toDto(item.getPaimentCollaboratorState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.inscriptionCollaborator = value;
        this.paimentCollaboratorState = value;
    }
	
    public List<PaimentCollaborator> toItem(List<PaimentCollaboratorDto> dtos) {
        List<PaimentCollaborator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaimentCollaboratorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaimentCollaboratorDto> toDto(List<PaimentCollaborator> items) {
        List<PaimentCollaboratorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (PaimentCollaborator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaimentCollaboratorDto dto, PaimentCollaborator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getInscriptionCollaborator() == null  && dto.getInscriptionCollaborator() != null){
            t.setInscriptionCollaborator(new InscriptionCollaborator());
        }
        if(t.getPaimentCollaboratorState() == null  && dto.getPaimentCollaboratorState() != null){
            t.setPaimentCollaboratorState(new PaimentCollaboratorState());
        }
        if (dto.getInscriptionCollaborator() != null)
        inscriptionCollaboratorConverter.copy(dto.getInscriptionCollaborator(), t.getInscriptionCollaborator());
        if (dto.getPaimentCollaboratorState() != null)
        paimentCollaboratorStateConverter.copy(dto.getPaimentCollaboratorState(), t.getPaimentCollaboratorState());
    }

    public List<PaimentCollaborator> copy(List<PaimentCollaboratorDto> dtos) {
        List<PaimentCollaborator> result = new ArrayList<>();
        if (dtos != null) {
            for (PaimentCollaboratorDto dto : dtos) {
                PaimentCollaborator instance = new PaimentCollaborator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PaimentCollaboratorStateConverter getPaimentCollaboratorStateConverter(){
        return this.paimentCollaboratorStateConverter;
    }
    public void setPaimentCollaboratorStateConverter(PaimentCollaboratorStateConverter paimentCollaboratorStateConverter ){
        this.paimentCollaboratorStateConverter = paimentCollaboratorStateConverter;
    }
    public InscriptionCollaboratorConverter getInscriptionCollaboratorConverter(){
        return this.inscriptionCollaboratorConverter;
    }
    public void setInscriptionCollaboratorConverter(InscriptionCollaboratorConverter inscriptionCollaboratorConverter ){
        this.inscriptionCollaboratorConverter = inscriptionCollaboratorConverter;
    }
    public boolean  isInscriptionCollaborator(){
        return this.inscriptionCollaborator;
    }
    public void  setInscriptionCollaborator(boolean inscriptionCollaborator){
        this.inscriptionCollaborator = inscriptionCollaborator;
    }
    public boolean  isPaimentCollaboratorState(){
        return this.paimentCollaboratorState;
    }
    public void  setPaimentCollaboratorState(boolean paimentCollaboratorState){
        this.paimentCollaboratorState = paimentCollaboratorState;
    }
}
