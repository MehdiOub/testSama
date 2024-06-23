package  ma.zs.zyn.ws.converter.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.zyn.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.zyn.zynerator.util.StringUtil;
import ma.zs.zyn.zynerator.converter.AbstractConverter;
import ma.zs.zyn.zynerator.util.DateUtil;
import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.ws.dto.template.TechnologyDto;

@Component
public class TechnologyConverter {


    public  TechnologyConverter() {
    }


    public Technology toItem(TechnologyDto dto) {
        if (dto == null) {
            return null;
        } else {
        Technology item = new Technology();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getLogo()))
                item.setLogo(dto.getLogo());



        return item;
        }
    }


    public TechnologyDto toDto(Technology item) {
        if (item == null) {
            return null;
        } else {
            TechnologyDto dto = new TechnologyDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getLogo()))
                dto.setLogo(item.getLogo());


        return dto;
        }
    }


	
    public List<Technology> toItem(List<TechnologyDto> dtos) {
        List<Technology> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TechnologyDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TechnologyDto> toDto(List<Technology> items) {
        List<TechnologyDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Technology item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TechnologyDto dto, Technology t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Technology> copy(List<TechnologyDto> dtos) {
        List<Technology> result = new ArrayList<>();
        if (dtos != null) {
            for (TechnologyDto dto : dtos) {
                Technology instance = new Technology();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
