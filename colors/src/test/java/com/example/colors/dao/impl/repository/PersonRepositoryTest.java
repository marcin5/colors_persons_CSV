package com.example.colors.dao.impl.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.colors.ObjectMother;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {
  
  @Autowired
  private PersonRepository repository;


  @Test
  public void shouldFindAll() {
    // when
    List<PersonEty> persons = this.repository.findAll();

    // then
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldFindById() {
    // when
    Optional<PersonEty> person = this.repository.findById(1L);

    // then
    assertTrue(person.isPresent());
    assertThat(person.get().getLastname()).isEqualTo("Müller");
  }

  @Test
  public void shouldFindByColor() {
    // when
    List<PersonEty> persons = this.repository.findByColor(Color.PURPLE);

    // then
    assertThat(persons.size()).isEqualTo(1);
  }

  @Test
  public void shouldAddPerson() {
    // given
    // assertTrue(this.dao.findAll().size() == 2);

    // when
    this.repository.save(ObjectMother.getPersonEtyWithId(3L));

    // then
    assertTrue(this.repository.findAll().size() == 3L);
  }
}
