package com.example.colors.dao.impl.csv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.ObjectMother;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@SpringBootTest
public class CsvPersonDaoTest {

  @Autowired
  private CsvPersonDao dao;

  @Test
  public void shouldFindAll() {
    // when
    List<PersonEty> persons = this.dao.findAll();

    // then
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldFindById() {
    // when
    Optional<PersonEty> person = this.dao.findById(1L);

    // then
    assertTrue(person.isPresent());
    assertThat(person.get().getLastname()).isEqualTo("Müller");
  }

  @Test
  public void shouldFindByColor() {
    // when
    List<PersonEty> persons = this.dao.findByColor(Color.PURPLE);

    // then
    assertThat(persons.size()).isEqualTo(1);
  }

  @Test
  public void shouldAddPerson() {
    // given
    // assertTrue(this.dao.findAll().size() == 2);

    // when
    this.dao.save(ObjectMother.getPersonEtyWithId(3L));

    // then
    assertTrue(this.dao.findAll().size() == 3L);
  }
}
