package ma.zs.zyn.unit.dao.facade.core.packaging;

import ma.zs.zyn.bean.core.packaging.CategoryPackaging;
import ma.zs.zyn.dao.facade.core.packaging.CategoryPackagingDao;

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
public class CategoryPackagingDaoTest {

@Autowired
    private CategoryPackagingDao underTest;

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        CategoryPackaging entity = new CategoryPackaging();
        entity.setCode(code);
        underTest.save(entity);
        CategoryPackaging loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-1";
        CategoryPackaging entity = new CategoryPackaging();
        entity.setCode(code);
        underTest.save(entity);

        int result = underTest.deleteByCode(code);

        CategoryPackaging loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        CategoryPackaging entity = new CategoryPackaging();
        entity.setId(id);
        underTest.save(entity);
        CategoryPackaging loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        CategoryPackaging entity = new CategoryPackaging();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        CategoryPackaging loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<CategoryPackaging> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<CategoryPackaging> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        CategoryPackaging given = constructSample(1);
        CategoryPackaging saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private CategoryPackaging constructSample(int i) {
		CategoryPackaging given = new CategoryPackaging();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
