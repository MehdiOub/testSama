package ma.zs.zyn.unit.ws.facade.admin.collaborator;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState;
import ma.zs.zyn.service.impl.admin.collaborator.PaimentCollaboratorStateAdminServiceImpl;
import ma.zs.zyn.ws.facade.admin.collaborator.PaimentCollaboratorStateRestAdmin;
import ma.zs.zyn.ws.converter.collaborator.PaimentCollaboratorStateConverter;
import ma.zs.zyn.ws.dto.collaborator.PaimentCollaboratorStateDto;
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
public class PaimentCollaboratorStateRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private PaimentCollaboratorStateAdminServiceImpl service;
    @Mock
    private PaimentCollaboratorStateConverter converter;

    @InjectMocks
    private PaimentCollaboratorStateRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllPaimentCollaboratorStateTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<PaimentCollaboratorStateDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<PaimentCollaboratorStateDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSavePaimentCollaboratorStateTest() throws Exception {
        // Mock data
        PaimentCollaboratorStateDto requestDto = new PaimentCollaboratorStateDto();
        PaimentCollaboratorState entity = new PaimentCollaboratorState();
        PaimentCollaboratorState saved = new PaimentCollaboratorState();
        PaimentCollaboratorStateDto savedDto = new PaimentCollaboratorStateDto();

        // Mock the converter to return the paimentCollaboratorState object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved paimentCollaboratorState DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<PaimentCollaboratorStateDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        PaimentCollaboratorStateDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved paimentCollaboratorState DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
