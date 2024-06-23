package ma.zs.zyn.unit.service.impl.admin.collaborator;

import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState;
import ma.zs.zyn.dao.facade.core.collaborator.InscriptionCollaboratorStateDao;
import ma.zs.zyn.service.impl.admin.collaborator.InscriptionCollaboratorStateAdminServiceImpl;

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
class InscriptionCollaboratorStateCollaboratorServiceImplTest {

    @Mock
    private InscriptionCollaboratorStateDao repository;
    private AutoCloseable autoCloseable;
    private InscriptionCollaboratorStateCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new InscriptionCollaboratorStateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllInscriptionCollaboratorState() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveInscriptionCollaboratorState() {
        // Given
        InscriptionCollaboratorState toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteInscriptionCollaboratorState() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetInscriptionCollaboratorStateById() {
        // Given
        Long idToRetrieve = 1L; // Example InscriptionCollaboratorState ID to retrieve
        InscriptionCollaboratorState expected = new InscriptionCollaboratorState(); // You need to replace InscriptionCollaboratorState with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        InscriptionCollaboratorState result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private InscriptionCollaboratorState constructSample(int i) {
		InscriptionCollaboratorState given = new InscriptionCollaboratorState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
