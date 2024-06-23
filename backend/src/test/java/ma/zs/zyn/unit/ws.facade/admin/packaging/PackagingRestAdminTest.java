package ma.zs.zyn.unit.ws.facade.admin.packaging;

import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.service.impl.admin.packaging.PackagingAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.packaging.PackagingRestAdmin;
import ma.zs.zyn.ws.converter.packaging.PackagingConverter;
import ma.zs.zyn.ws.dto.packaging.PackagingDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PackagingRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private PackagingAdminServiceImpl service;
    @Mock
    private PackagingConverter converter;

    @InjectMocks
    private PackagingRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllPackagingTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<PackagingDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<PackagingDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSavePackagingTest() throws Exception {
        // Mock data
        PackagingDto requestDto = new PackagingDto();
        Packaging entity = new Packaging();
        Packaging saved = new Packaging();
        PackagingDto savedDto = new PackagingDto();

        // Mock the converter to return the packaging object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved packaging DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<PackagingDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        PackagingDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved packaging DTO
        assertEquals(savedDto.getName(), responseBody.getName());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getDateStart(), responseBody.getDateStart());
        assertEquals(savedDto.getDateEnd(), responseBody.getDateEnd());
        assertEquals(savedDto.getPrice(), responseBody.getPrice());
        assertEquals(savedDto.getMaxEntity(), responseBody.getMaxEntity());
        assertEquals(savedDto.getMaxProjet(), responseBody.getMaxProjet());
        assertEquals(savedDto.getMaxAttribut(), responseBody.getMaxAttribut());
        assertEquals(savedDto.getMaxIndicator(), responseBody.getMaxIndicator());
    }

}
