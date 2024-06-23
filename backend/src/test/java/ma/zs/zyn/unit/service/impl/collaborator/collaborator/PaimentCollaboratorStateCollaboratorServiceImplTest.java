package ma.zs.zyn.unit.service.impl.admin.collaborator;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState;
import ma.zs.zyn.dao.facade.core.collaborator.PaimentCollaboratorStateDao;
import ma.zs.zyn.service.impl.admin.collaborator.PaimentCollaboratorStateAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PaimentCollaboratorStateCollaboratorServiceImplTest {

    @Mock
    private PaimentCollaboratorStateDao repository;
    private AutoCloseable autoCloseable;
    private PaimentCollaboratorStateCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PaimentCollaboratorStateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPaimentCollaboratorState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePaimentCollaboratorState() {
        // Given
        PaimentCollaboratorState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePaimentCollaboratorState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPaimentCollaboratorStateById() {
        // Given
        Long idToRetrieve = 1L; // Example PaimentCollaboratorState ID to retrieve
        PaimentCollaboratorState expected = new PaimentCollaboratorState(); // You need to replace PaimentCollaboratorState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        PaimentCollaboratorState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private PaimentCollaboratorState constructSample(int i) {
		PaimentCollaboratorState given = new PaimentCollaboratorState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
