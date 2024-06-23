package ma.zs.zyn.unit.dao.facade.core.template;

import ma.zs.zyn.bean.core.template.Technology;
import ma.zs.zyn.dao.facade.core.template.TechnologyDao;

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
public class TechnologyDaoTest {

@Autowired
    private TechnologyDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Technology entity = new Technology();
        entity.setCode(code);
        underTest.save(entity);
        Technology loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        Technology entity = new Technology();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        Technology loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Technology entity = new Technology();
        entity.setId(id);
        underTest.save(entity);
        Technology loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Technology entity = new Technology();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Technology loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Technology> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Technology> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Technology given = constructSample(1);
        Technology saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Technology constructSample(int i) {
		Technology given = new Technology();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setLogo("logo-"+i);
        return given;
    }

}
