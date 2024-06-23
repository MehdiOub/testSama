package ma.zs.zyn.unit.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.PaimentCollaboratorState;
import ma.zs.zyn.dao.facade.core.collaborator.PaimentCollaboratorStateDao;

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
public class PaimentCollaboratorStateDaoTest {

@Autowired
    private PaimentCollaboratorStateDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        PaimentCollaboratorState entity = new PaimentCollaboratorState();
        entity.setCode(code);
        underTest.save(entity);
        PaimentCollaboratorState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        PaimentCollaboratorState entity = new PaimentCollaboratorState();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        PaimentCollaboratorState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        PaimentCollaboratorState entity = new PaimentCollaboratorState();
        entity.setId(id);
        underTest.save(entity);
        PaimentCollaboratorState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        PaimentCollaboratorState entity = new PaimentCollaboratorState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        PaimentCollaboratorState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<PaimentCollaboratorState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<PaimentCollaboratorState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        PaimentCollaboratorState given = constructSample(1);
        PaimentCollaboratorState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private PaimentCollaboratorState constructSample(int i) {
		PaimentCollaboratorState given = new PaimentCollaboratorState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
