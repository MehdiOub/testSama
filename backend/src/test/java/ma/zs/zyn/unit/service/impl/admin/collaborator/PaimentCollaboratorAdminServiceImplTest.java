package ma.zs.zyn.unit.service.impl.admin.collaborator;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.dao.facade.core.collaborator.PaimentCollaboratorDao;
import ma.zs.zyn.service.impl.admin.collaborator.PaimentCollaboratorAdminServiceImpl;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState ;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator ;
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
class PaimentCollaboratorAdminServiceImplTest {

    @Mock
    private PaimentCollaboratorDao repository;
    private AutoCloseable autoCloseable;
    private PaimentCollaboratorAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PaimentCollaboratorAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPaimentCollaborator() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePaimentCollaborator() {
        // Given
        PaimentCollaborator toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePaimentCollaborator() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPaimentCollaboratorById() {
        // Given
        Long idToRetrieve = 1L; // Example PaimentCollaborator ID to retrieve
        PaimentCollaborator expected = new PaimentCollaborator(); // You need to replace PaimentCollaborator with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        PaimentCollaborator result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private PaimentCollaborator constructSample(int i) {
		PaimentCollaborator given = new PaimentCollaborator();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setCode("code-"+i);
        given.setAmountToPaid(BigDecimal.TEN);
        given.setTotal(BigDecimal.TEN);
        given.setDiscount(BigDecimal.TEN);
        given.setRemaining(BigDecimal.TEN);
        given.setPaiementDate(LocalDateTime.now());
        given.setInscriptionCollaborator(new InscriptionCollaborator(1L));
        given.setPaimentCollaboratorState(new PaimentCollaboratorState(1L));
        return given;
    }

}
