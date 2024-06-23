package  ma.zs.zyn.ws.facade.admin.template;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.criteria.core.template.TechnologyCriteria;
import ma.zs.zyn.service.facade.admin.template.TechnologyAdminService;
import ma.zs.zyn.ws.converter.template.TechnologyConverter;
import ma.zs.zyn.ws.dto.template.TechnologyDto;
import ma.zs.zyn.zynerator.controller.AbstractController;
import ma.zs.zyn.zynerator.dto.AuditEntityDto;
import ma.zs.zyn.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.zyn.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.zyn.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/technology/")
public class TechnologyRestAdmin {




    @Operation(summary = "Finds a list of all technologys")
    @GetMapping("")
    public ResponseEntity<List<TechnologyDto>> findAll() throws Exception {
        ResponseEntity<List<TechnologyDto>> res = null;
        List<Technology> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TechnologyDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all technologys")
    @GetMapping("optimized")
    public ResponseEntity<List<TechnologyDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TechnologyDto>> res = null;
        List<Technology> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TechnologyDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a technology by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TechnologyDto> findById(@PathVariable Long id) {
        Technology t = service.findById(id);
        if (t != null) {
            TechnologyDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a technology by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TechnologyDto> findByLibelle(@PathVariable String libelle) {
	    Technology t = service.findByReferenceEntity(new Technology(libelle));
        if (t != null) {
            TechnologyDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  technology")
    @PostMapping("")
    public ResponseEntity<TechnologyDto> save(@RequestBody TechnologyDto dto) throws Exception {
        if(dto!=null){
            Technology myT = converter.toItem(dto);
            Technology t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TechnologyDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  technology")
    @PutMapping("")
    public ResponseEntity<TechnologyDto> update(@RequestBody TechnologyDto dto) throws Exception {
        ResponseEntity<TechnologyDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Technology t = service.findById(dto.getId());
            converter.copy(dto,t);
            Technology updated = service.update(t);
            TechnologyDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of technology")
    @PostMapping("multiple")
    public ResponseEntity<List<TechnologyDto>> delete(@RequestBody List<TechnologyDto> dtos) throws Exception {
        ResponseEntity<List<TechnologyDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Technology> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified technology")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a technology and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TechnologyDto> findWithAssociatedLists(@PathVariable Long id) {
        Technology loaded =  service.findWithAssociatedLists(id);
        TechnologyDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds technologys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TechnologyDto>> findByCriteria(@RequestBody TechnologyCriteria criteria) throws Exception {
        ResponseEntity<List<TechnologyDto>> res = null;
        List<Technology> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TechnologyDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated technologys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TechnologyCriteria criteria) throws Exception {
        List<Technology> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TechnologyDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets technology data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TechnologyCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TechnologyDto> findDtos(List<Technology> list){
        List<TechnologyDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TechnologyDto> getDtoResponseEntity(TechnologyDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TechnologyAdminService service;
    @Autowired private TechnologyConverter converter;





}
