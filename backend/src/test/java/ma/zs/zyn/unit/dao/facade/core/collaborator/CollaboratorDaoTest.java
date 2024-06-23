package ma.zs.zyn.unit.dao.facade.core.collaborator;

import ma.zs.zyn.bean.core.collaborator.Collaborator;
import ma.zs.zyn.dao.facade.core.collaborator.CollaboratorDao;

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
public class CollaboratorDaoTest {

@Autowired
    private CollaboratorDao underTest;


    @Test
    void shouldFindById(){
        Long id = 1L;
        Collaborator entity = new Collaborator();
        entity.setId(id);
        underTest.save(entity);
        Collaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Collaborator entity = new Collaborator();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Collaborator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Collaborator> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Collaborator> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Collaborator given = constructSample(1);
        Collaborator saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Collaborator constructSample(int i) {
		Collaborator given = new Collaborator();
        given.setDescription("description-"+i);
        given.setCredentialsNonExpired(false);
        given.setEnabled(false);
        given.setAccountNonExpired(false);
        given.setAccountNonLocked(false);
        given.setPasswordChanged(false);
        given.setUsername("username-"+i);
        given.setPassword("password-"+i);
        return given;
    }

}
