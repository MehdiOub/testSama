package ma.zs.zyn.unit.ws.facade.admin.template;

import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.service.impl.admin.template.ProjectTemplateAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.template.ProjectTemplateRestAdmin;
import ma.zs.zyn.ws.converter.template.ProjectTemplateConverter;
import ma.zs.zyn.ws.dto.template.ProjectTemplateDto;
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
public class ProjectTemplateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectTemplateAdminServiceImpl service;
    @Mock
    private ProjectTemplateConverter converter;

    @InjectMocks
    private ProjectTemplateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProjectTemplateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProjectTemplateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProjectTemplateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProjectTemplateTest() throws Exception {
        // Mock data
        ProjectTemplateDto requestDto = new ProjectTemplateDto();
        ProjectTemplate entity = new ProjectTemplate();
        ProjectTemplate saved = new ProjectTemplate();
        ProjectTemplateDto savedDto = new ProjectTemplateDto();

        // Mock the converter to return the projectTemplate object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved projectTemplate DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProjectTemplateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProjectTemplateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved projectTemplate DTO
    }

}
