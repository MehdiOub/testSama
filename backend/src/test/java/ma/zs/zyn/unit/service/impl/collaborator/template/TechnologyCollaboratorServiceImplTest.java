package ma.zs.zyn.unit.service.impl.admin.template;

import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.facade.core.template.TechnologyDao;
import ma.zs.zyn.service.impl.admin.template.TechnologyAdminServiceImpl;

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
class TechnologyCollaboratorServiceImplTest {

    @Mock
    private TechnologyDao repository;
    private AutoCloseable autoCloseable;
    private TechnologyCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TechnologyAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTechnology() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTechnology() {
        // Given
        Technology toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTechnology() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTechnologyById() {
        // Given
        Long idToRetrieve = 1L; // Example Technology ID to retrieve
        Technology expected = new Technology(); // You need to replace Technology with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Technology result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Technology constructSample(int i) {
		Technology given = new Technology();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setLogo("logo-"+i);
        return given;
    }

}
