package ma.zs.zyn.unit.ws.facade.admin.project;

import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.service.impl.admin.project.ProjectStateAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.project.ProjectStateRestAdmin;
import ma.zs.zyn.ws.converter.project.ProjectStateConverter;
import ma.zs.zyn.ws.dto.project.ProjectStateDto;
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
public class ProjectStateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectStateAdminServiceImpl service;
    @Mock
    private ProjectStateConverter converter;

    @InjectMocks
    private ProjectStateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProjectStateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProjectStateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProjectStateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProjectStateTest() throws Exception {
        // Mock data
        ProjectStateDto requestDto = new ProjectStateDto();
        ProjectState entity = new ProjectState();
        ProjectState saved = new ProjectState();
        ProjectStateDto savedDto = new ProjectStateDto();

        // Mock the converter to return the projectState object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved projectState DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProjectStateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProjectStateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved projectState DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
