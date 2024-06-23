package ma.zs.zyn.unit.dao.facade.core.project;

import ma.zs.zyn.bean.core.project.ProjectState;
import ma.zs.zyn.dao.facade.core.project.ProjectStateDao;

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
public class ProjectStateDaoTest {

@Autowired
    private ProjectStateDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ProjectState entity = new ProjectState();
        entity.setCode(code);
        underTest.save(entity);
        ProjectState loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        ProjectState entity = new ProjectState();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        ProjectState loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ProjectState entity = new ProjectState();
        entity.setId(id);
        underTest.save(entity);
        ProjectState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProjectState entity = new ProjectState();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProjectState loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProjectState> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProjectState> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProjectState given = constructSample(1);
        ProjectState saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProjectState constructSample(int i) {
		ProjectState given = new ProjectState();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
