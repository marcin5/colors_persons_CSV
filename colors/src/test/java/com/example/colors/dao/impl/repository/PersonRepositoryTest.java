package com.example.colors.dao.impl.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.colors.ObjectMother;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@DataJpaTest
//@PropertySource("src/test/resources/testData.sql")
public class PersonRepositoryTest {
  
  @Autowired
  private PersonRepository repository;

  @Test
  public void shouldFindAll() {
    // when
    List<PersonEty> persons = this.repository.findAll();

    // then
    assertEquals(2, persons.size());
  }

  @Test
  public void shouldFindById() {
    // when
    Optional<PersonEty> person = this.repository.findById(1L);

    // then
    assertTrue(person.isPresent());
    assertEquals("Müller", person.get().getLastname());
  }

  @Test
  public void shouldFindByColor() {
    // when
    List<PersonEty> persons = this.repository.findByColor(Color.PURPLE);

    // then
    assertEquals(1, persons.size());
  }

  @Test
  public void shouldAddPerson() {
    // given
    assertEquals(2L, this.repository.findAll().size());

    // when
    this.repository.save(ObjectMother.getPersonEtyWithId(3L));

    // then
    assertEquals(3L, this.repository.findAll().size());
  }
}
