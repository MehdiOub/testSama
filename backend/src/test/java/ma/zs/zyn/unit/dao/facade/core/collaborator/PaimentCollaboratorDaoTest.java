package ma.zs.zyn.unit.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaborator;
import ma.zs.zyn.dao.facade.core.collaborator.PaimentCollaboratorDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState ;
import ma.zs.zyn.bean.core.collaborator.InscriptionCollaborator ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PaimentCollaboratorDaoTest {

@Autowired
    private PaimentCollaboratorDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        PaimentCollaborator entity = new PaimentCollaborator();
        entity.setCode(code);
        underTest.save(entity);
        PaimentCollaborator loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        PaimentCollaborator entity = new PaimentCollaborator();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        PaimentCollaborator loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        PaimentCollaborator entity = new PaimentCollaborator();
        entity.setId(id);
        underTest.save(entity);
        PaimentCollaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        PaimentCollaborator entity = new PaimentCollaborator();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        PaimentCollaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<PaimentCollaborator> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<PaimentCollaborator> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        PaimentCollaborator given = constructSample(1);
        PaimentCollaborator saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
