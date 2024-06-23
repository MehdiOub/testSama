package ma.zs.zyn.unit.dao.facade.core.project;

import ma.zs.zyn.bean.core.project.Project;
import ma.zs.zyn.dao.facade.core.project.ProjectDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.zyn.bean.core.project.ProjectState ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectDaoTest {

@Autowired
    private ProjectDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Project entity = new Project();
        entity.setCode(code);
        underTest.save(entity);
        Project loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        Project entity = new Project();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        Project loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Project entity = new Project();
        entity.setId(id);
        underTest.save(entity);
        Project loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Project entity = new Project();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Project loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Project> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Project> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Project given = constructSample(1);
        Project saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Project constructSample(int i) {
		Project given = new Project();
        given.setCode("code-"+i);
        given.setName("name-"+i);
        given.setGeneratedDate(LocalDateTime.now());
        given.setYaml("yaml-"+i);
        given.setProjectState(new ProjectState(1L));
        return given;
    }

}
