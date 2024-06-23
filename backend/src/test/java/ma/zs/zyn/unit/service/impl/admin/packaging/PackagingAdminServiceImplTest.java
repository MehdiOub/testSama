package ma.zs.zyn.unit.service.impl.admin.packaging;

import ma.zs.zyn.bean.core.packaging.Packaging;
import ma.zs.zyn.dao.facade.core.packaging.PackagingDao;
import ma.zs.zyn.service.impl.admin.packaging.PackagingAdminServiceImpl;

import ma.zs.zyn.bean.core.packaging.CategoryPackaging ;
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
class PackagingAdminServiceImplTest {

    @Mock
    private PackagingDao repository;
    private AutoCloseable autoCloseable;
    private PackagingAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PackagingAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPackaging() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePackaging() {
        // Given
        Packaging toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePackaging() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPackagingById() {
        // Given
        Long idToRetrieve = 1L; // Example Packaging ID to retrieve
        Packaging expected = new Packaging(); // You need to replace Packaging with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Packaging result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Packaging constructSample(int i) {
		Packaging given = new Packaging();
        given.setName("name-"+i);
        given.setCode("code-"+i);
        given.setDescription("description-"+i);
        given.setDateStart(LocalDateTime.now());
        given.setDateEnd(LocalDateTime.now());
        given.setPrice(BigDecimal.TEN);
        given.setMaxEntity(BigDecimal.TEN);
        given.setMaxProjet(BigDecimal.TEN);
        given.setMaxAttribut(BigDecimal.TEN);
        given.setMaxIndicator(BigDecimal.TEN);
        given.setCategoryPackaging(new CategoryPackaging(1L));
        return given;
    }

}
