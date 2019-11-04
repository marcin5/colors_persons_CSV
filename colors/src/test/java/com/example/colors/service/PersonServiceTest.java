package com.example.colors.service;

import static com.example.colors.ObjectMother.getPersonTOWithId;
import static com.example.colors.ObjectMother.getPersonWithId;
import static com.example.colors.ObjectMother.getRandomPersons;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.colors.dao.PersonDAO;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.model.Color;
import com.example.colors.model.entity.Person;
import com.example.colors.model.to.PersonTO;

@SpringBootTest
public class PersonServiceTest {

  @MockBean
  private PersonDAO<Person> personRepository;

  @Autowired
  private PersonService personService;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldReturnAllPersons() {
    // given
    when(this.personRepository.findAll())
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
    Person person = getPersonWithId(personId);
    when(this.personRepository.findById(personId))
        .thenReturn(Optional.of(person));

    // when
    PersonTO personDTO = this.personService.getPersonById(1L);

    // then
    assertThat(personDTO.getLastname()).isEqualTo("Big");
  }

  @Test
  public void shouldThrowExceptionWhenNoPersonForId() {
    // given
    when(this.personRepository.findById(1L))
        .thenReturn(Optional.empty());

    // when
    // TODO assert junit5
    try {
      this.personService.getPersonById(1L);
      Assert.fail("schould throw an exception");
    } catch (NoPersonFoundException e) { // then
    }
  }

  @Test
  public void shouldReturnPersonsByColor() {
    // given
    when(this.personRepository.findByColor(Color.BLUE))
        .thenReturn(getRandomPersons());

    // when
    List<PersonTO> persons = this.personService.getAllByColor(Color.BLUE);

    // then
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldAddPerson() {
    // when
    this.personService.addPerson(getPersonTOWithId(1L));

    // then
    verify(this.personRepository, atLeastOnce()).save(Mockito.any(Person.class));
  }
}
