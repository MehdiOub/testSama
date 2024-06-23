package ma.zs.zyn.unit.ws.facade.admin.packaging;

import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.service.impl.admin.packaging.CategoryPackagingAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.packaging.CategoryPackagingRestAdmin;
import ma.zs.zyn.ws.converter.packaging.CategoryPackagingConverter;
import ma.zs.zyn.ws.dto.packaging.CategoryPackagingDto;
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
public class CategoryPackagingRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryPackagingAdminServiceImpl service;
    @Mock
    private CategoryPackagingConverter converter;

    @InjectMocks
    private CategoryPackagingRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCategoryPackagingTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CategoryPackagingDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CategoryPackagingDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCategoryPackagingTest() throws Exception {
        // Mock data
        CategoryPackagingDto requestDto = new CategoryPackagingDto();
        CategoryPackaging entity = new CategoryPackaging();
        CategoryPackaging saved = new CategoryPackaging();
        CategoryPackagingDto savedDto = new CategoryPackagingDto();

        // Mock the converter to return the categoryPackaging object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved categoryPackaging DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CategoryPackagingDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CategoryPackagingDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved categoryPackaging DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
