package ma.zs.zyn.unit.ws.facade.admin.template;

import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.service.impl.admin.template.TemplateAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.template.TemplateRestAdmin;
import ma.zs.zyn.ws.converter.template.TemplateConverter;
import ma.zs.zyn.ws.dto.template.TemplateDto;
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
public class TemplateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TemplateAdminServiceImpl service;
    @Mock
    private TemplateConverter converter;

    @InjectMocks
    private TemplateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTemplateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TemplateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TemplateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTemplateTest() throws Exception {
        // Mock data
        TemplateDto requestDto = new TemplateDto();
        Template entity = new Template();
        Template saved = new Template();
        TemplateDto savedDto = new TemplateDto();

        // Mock the converter to return the template object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved template DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TemplateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TemplateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved template DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getAddingDate(), responseBody.getAddingDate());
        assertEquals(savedDto.getLastUpdateDate(), responseBody.getLastUpdateDate());
        assertEquals(savedDto.getTemplateTags(), responseBody.getTemplateTags());
        assertEquals(savedDto.getPrice(), responseBody.getPrice());
    }

}
