package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.zyn.ws.converter.template.TechnologyConverter;
import ma.zs.zyn.bean.core.template.Technology;



import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.ws.dto.template.TemplateDto;

@Component
public class TemplateConverter {

    @Autowired
    private TechnologyConverter technologyConverter ;
    private boolean technology;

    public  TemplateConverter() {
        initObject(true);
    }


    public Template toItem(TemplateDto dto) {
        if (dto == null) {
            return null;
        } else {
        Template item = new Template();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getAddingDate()))
                item.setAddingDate(DateUtil.stringEnToDate(dto.getAddingDate()));
            if(StringUtil.isNotEmpty(dto.getLastUpdateDate()))
                item.setLastUpdateDate(DateUtil.stringEnToDate(dto.getLastUpdateDate()));
            if(StringUtil.isNotEmpty(dto.getTemplateTags()))
                item.setTemplateTags(dto.getTemplateTags());
            if(StringUtil.isNotEmpty(dto.getPrice()))
                item.setPrice(dto.getPrice());
            if(this.technology && dto.getTechnology()!=null)
                item.setTechnology(technologyConverter.toItem(dto.getTechnology())) ;




        return item;
        }
    }


    public TemplateDto toDto(Template item) {
        if (item == null) {
            return null;
        } else {
            TemplateDto dto = new TemplateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getAddingDate()!=null)
                dto.setAddingDate(DateUtil.dateTimeToString(item.getAddingDate()));
            if(item.getLastUpdateDate()!=null)
                dto.setLastUpdateDate(DateUtil.dateTimeToString(item.getLastUpdateDate()));
            if(StringUtil.isNotEmpty(item.getTemplateTags()))
                dto.setTemplateTags(item.getTemplateTags());
            if(StringUtil.isNotEmpty(item.getPrice()))
                dto.setPrice(item.getPrice());
            if(this.technology && item.getTechnology()!=null) {
                dto.setTechnology(technologyConverter.toDto(item.getTechnology())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.technology = value;
    }
	
    public List<Template> toItem(List<TemplateDto> dtos) {
        List<Template> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TemplateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TemplateDto> toDto(List<Template> items) {
        List<TemplateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Template item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TemplateDto dto, Template t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTechnology() == null  && dto.getTechnology() != null){
            t.setTechnology(new Technology());
        }
        if (dto.getTechnology() != null)
        technologyConverter.copy(dto.getTechnology(), t.getTechnology());
    }

    public List<Template> copy(List<TemplateDto> dtos) {
        List<Template> result = new ArrayList<>();
        if (dtos != null) {
            for (TemplateDto dto : dtos) {
                Template instance = new Template();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TechnologyConverter getTechnologyConverter(){
        return this.technologyConverter;
    }
    public void setTechnologyConverter(TechnologyConverter technologyConverter ){
        this.technologyConverter = technologyConverter;
    }
    public boolean  isTechnology(){
        return this.technology;
    }
    public void  setTechnology(boolean technology){
        this.technology = technology;
    }
}
