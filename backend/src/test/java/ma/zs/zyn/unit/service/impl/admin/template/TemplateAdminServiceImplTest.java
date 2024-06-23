package ma.zs.zyn.unit.service.impl.admin.template;

import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.facade.core.template.TemplateDao;
import ma.zs.zyn.service.impl.admin.template.TemplateAdminServiceImpl;

import ma.zs.zyn.bean.core.template.Technology ;
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
class TemplateAdminServiceImplTest {

    @Mock
    private TemplateDao repository;
    private AutoCloseable autoCloseable;
    private TemplateAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TemplateAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTemplate() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTemplate() {
        // Given
        Template toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTemplate() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTemplateById() {
        // Given
        Long idToRetrieve = 1L; // Example Template ID to retrieve
        Template expected = new Template(); // You need to replace Template with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Template result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Template constructSample(int i) {
		Template given = new Template();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setAddingDate(LocalDateTime.now());
        given.setLastUpdateDate(LocalDateTime.now());
        given.setTemplateTags("templateTags-"+i);
        given.setPrice(BigDecimal.TEN);
        given.setTechnology(new Technology(1L));
        return given;
    }

}
