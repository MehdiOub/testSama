package ma.zs.zyn.unit.dao.facade.core.template;

import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.dao.facade.core.template.TemplateDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zs.zyn.bean.core.template.Technology ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TemplateDaoTest {

@Autowired
    private TemplateDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Template entity = new Template();
        entity.setCode(code);
        underTest.save(entity);
        Template loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        Template entity = new Template();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        Template loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Template entity = new Template();
        entity.setId(id);
        underTest.save(entity);
        Template loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Template entity = new Template();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Template loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Template> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Template> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Template given = constructSample(1);
        Template saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
