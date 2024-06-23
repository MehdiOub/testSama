package ma.zs.zyn.unit.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.InscriptionCollaboratorState;
import ma.zs.zyn.dao.facade.core.collaborator.InscriptionCollaboratorStateDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class InscriptionCollaboratorStateDaoTest {

@Autowired
    private InscriptionCollaboratorStateDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        InscriptionCollaboratorState entity = new InscriptionCollaboratorState();
        entity.setCode(code);
        underTest.save(entity);
        InscriptionCollaboratorState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        InscriptionCollaboratorState entity = new InscriptionCollaboratorState();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        InscriptionCollaboratorState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        InscriptionCollaboratorState entity = new InscriptionCollaboratorState();
        entity.setId(id);
        underTest.save(entity);
        InscriptionCollaboratorState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        InscriptionCollaboratorState entity = new InscriptionCollaboratorState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        InscriptionCollaboratorState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<InscriptionCollaboratorState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<InscriptionCollaboratorState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        InscriptionCollaboratorState given = constructSample(1);
        InscriptionCollaboratorState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private InscriptionCollaboratorState constructSample(int i) {
		InscriptionCollaboratorState given = new InscriptionCollaboratorState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
