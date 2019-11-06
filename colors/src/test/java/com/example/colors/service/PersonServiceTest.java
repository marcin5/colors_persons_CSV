package com.example.colors.service;

import static com.example.colors.ObjectMother.getPersonEtyWithId;
import static com.example.colors.ObjectMother.getPersonTOWithId;
import static com.example.colors.ObjectMother.getRandomPersons;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.colors.dao.PersonDAO;
import com.example.colors.dao.impl.csv.CsvPersonDao;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.exceptions.UpdateNotAvailableException;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;
import com.example.colors.model.to.PersonTO;

@SpringBootTest
public class PersonServiceTest {

  @MockBean(classes = CsvPersonDao.class)
  private PersonDAO<PersonEty> csvDao;

  @Autowired
  private PersonService personService;

  @Test
  public void shouldReturnAllPersons() {
    // given
    when(this.csvDao.findAll())
        .thenReturn(getRandomPersons());

    // when
    List<PersonTO> persons = this.personService.getAllPersons();

    // then
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldReturnPersonById() {
    // given
    long personId = 1L;
    PersonEty person = getPersonEtyWithId(personId);
    when(this.csvDao.findById(personId))
        .thenReturn(Optional.of(person));

    // when
    PersonTO personDTO = this.personService.getPersonById(1L);

    // then
    assertThat(personDTO.getLastname()).isEqualTo("Big");
  }

  @Test
  public void shouldThrowExceptionWhenNoPersonForId() {
    // given
    when(this.csvDao.findById(1L))
        .thenReturn(Optional.empty());

    // when //then
    Assertions.assertThrows(NoPersonFoundException.class, () -> {
      this.personService.getPersonById(1L);
    });
  }

  @Test
  public void shouldReturnPersonsByColor() {
    // given
    when(this.csvDao.findByColor(Color.BLUE))
        .thenReturn(getRandomPersons());

    // when
    List<PersonTO> persons = this.personService.getAllByColor(Color.BLUE);

    // then
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldAddPerson() {
    // when
    this.personService.addPerson(getPersonTOWithId(null));

    // then
    verify(this.csvDao, atLeastOnce()).save(Mockito.any(PersonEty.class));
  }

  @Test
  public void shouldThrowExceptionWhenPersonWithId() {
    // given // when //then
    Assertions.assertThrows(UpdateNotAvailableException.class, () -> {
      this.personService.addPerson(getPersonTOWithId(1L));
    });
  }
}
