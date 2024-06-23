package ma.zs.zyn.unit.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator;
import ma.zs.zyn.dao.facade.core.collaborator.InscriptionCollaboratorDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.zyn.bean.core.collaborator.Collaborator ;
import ma.zs.zyn.bean.core.packaging.Packaging ;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InscriptionCollaboratorDaoTest {

@Autowired
    private InscriptionCollaboratorDao underTest;

    @Test
    void shouldFindByReference(){
        String reference = "reference-1";
        InscriptionCollaborator entity = new InscriptionCollaborator();
        entity.setReference(reference);
        underTest.save(entity);
        InscriptionCollaborator loaded = underTest.findByReference(reference);
        assertThat(loaded.getReference()).isEqualTo(reference);
    }

    @Test
    void shouldDeleteByReference() {
        String reference = "reference-1";
        InscriptionCollaborator entity = new InscriptionCollaborator();
        entity.setReference(reference);
        underTest.save(entity);

        int result = underTest.deleteByReference(reference);

        InscriptionCollaborator loaded = underTest.findByReference(reference);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        InscriptionCollaborator entity = new InscriptionCollaborator();
        entity.setId(id);
        underTest.save(entity);
        InscriptionCollaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        InscriptionCollaborator entity = new InscriptionCollaborator();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        InscriptionCollaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<InscriptionCollaborator> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<InscriptionCollaborator> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        InscriptionCollaborator given = constructSample(1);
        InscriptionCollaborator saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private InscriptionCollaborator constructSample(int i) {
		InscriptionCollaborator given = new InscriptionCollaborator();
        given.setReference("reference-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setRenewDate(LocalDateTime.now());
        given.setPackaging(new Packaging(1L));
        given.setConsumedEntity(BigDecimal.TEN);
        given.setConsumedProjet(BigDecimal.TEN);
        given.setConsumedAttribut(BigDecimal.TEN);
        given.setConsumedIndicator(BigDecimal.TEN);
        given.setCollaborator(new Collaborator(1L));
        given.setInscriptionCollaboratorState(new InscriptionCollaboratorState(1L));
        return given;
    }

}
