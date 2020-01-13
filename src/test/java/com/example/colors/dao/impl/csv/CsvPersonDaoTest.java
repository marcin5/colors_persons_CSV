package com.example.colors.dao.impl.csv;

import static com.example.colors.ObjectMother.getPersonCsvBuilderWithId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import com.example.colors.ObjectMother;
import com.example.colors.dao.impl.csv.parser.CsvPersonParser;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@SpringBootTest
@TestPropertySource(properties = "csv.path=src/test/resources/testPersons.csv")
public class CsvPersonDaoTest {

  @Autowired
  private CsvPersonDao dao;

  @MockBean
  private CsvPersonParser csvParser;

  @BeforeEach
  public void setUp() {
    when(this.csvParser.getAllFromFile(anyString())).thenReturn(getPersonsCsv());
  }

  @Test
  public void shouldFindAll() {
    // when
    List<PersonEty> persons = this.dao.findAll();

    // then
    assertEquals(3, persons.size());
  }

  @Test
  public void shouldFindById() {
    // when
    Optional<PersonEty> person = this.dao.findById(1L);

    // then
    assertTrue(person.isPresent());
    assertEquals(1L, person.get().getId().longValue());
  }

  @Test
  public void shouldFindByColor() {
    // when
    List<PersonEty> persons = this.dao.findByColor(Color.PURPLE);

    // then
    assertEquals(1, persons.size());
  }

  @Test
  public void shouldAddPerson() {
    // given
    PersonEty personToSave = ObjectMother.getPersonEtyWithId(3L);

    // when
    PersonEty personReturnd = this.dao.save(personToSave);

    // then
    assertEquals(personToSave, personReturnd);
    verify(csvParser).addToFile(any(PersonCsv.class), anyString());
  }

  private List<PersonCsv> getPersonsCsv() {
    return Arrays.asList(
        getPersonCsvBuilderWithId(1L)
            .color(Color.BLUE.getValue()).build(),
        getPersonCsvBuilderWithId(2L)
            .color(Color.GREEN.getValue()).build(),
        getPersonCsvBuilderWithId(3L)
            .color(Color.PURPLE.getValue()).build());
  }
}
