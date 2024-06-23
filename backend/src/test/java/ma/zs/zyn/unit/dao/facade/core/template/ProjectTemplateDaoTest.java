package ma.zs.zyn.unit.dao.facade.core.template;

import ma.zs.zyn.bean.core.template.ProjectTemplate;
import ma.zs.zyn.dao.facade.core.template.ProjectTemplateDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.zyn.bean.core.project.Project ;
import ma.zs.zyn.bean.core.template.Template ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProjectTemplateDaoTest {

@Autowired
    private ProjectTemplateDao underTest;


    @Test
    void shouldFindById(){
        Long id = 1L;
        ProjectTemplate entity = new ProjectTemplate();
        entity.setId(id);
        underTest.save(entity);
        ProjectTemplate loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProjectTemplate entity = new ProjectTemplate();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProjectTemplate loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProjectTemplate> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProjectTemplate> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProjectTemplate given = constructSample(1);
        ProjectTemplate saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProjectTemplate constructSample(int i) {
		ProjectTemplate given = new ProjectTemplate();
        given.setTemplate(new Template(1L));
        given.setProject(new Project(1L));
        return given;
    }

}
