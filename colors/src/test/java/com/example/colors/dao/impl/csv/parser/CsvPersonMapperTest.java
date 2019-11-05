package com.example.colors.dao.impl.csv.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.ObjectMother;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@SpringBootTest
public class CsvPersonMapperTest {

  @Autowired
  private CsvPersonMapper mapper;
  
  @Test
  public void shouldMapPersonCsvObjetToEntity() {
    // given
    PersonCsv csvPerson = ObjectMother.getPersonCsvWithId(1L);
    
    // when
    PersonEty person = this.mapper.mapToEntity(csvPerson);

    // then
    assertEquals(1L, person.getId().longValue());
    assertEquals("Patrick", person.getName());
    assertEquals("Big", person.getLastname());
    assertEquals(50345L, person.getZipcode().longValue());
    assertEquals("Wroclaw", person.getCity());
    assertEquals(Color.BLUE, person.getColor());
  }
  
  @Test
  public void shouldMapEntityToPersonCsvObject() {
    // given
    PersonEty person = ObjectMother.getPersonEtyWithId(1L);
    
    // when
    PersonCsv personCsv = this.mapper.mapToCsv(person);

    // then
    assertEquals(1L, personCsv.getId().longValue());
    assertEquals("Patrick", personCsv.getName());
    assertEquals("Big", personCsv.getLastname());
    assertEquals("32456 Wroclaw", personCsv.getZipcodeAndCity());
    assertEquals(1, personCsv.getColor().intValue());
  }
  
  @Test
  public void shouldMapZipcodeAndCityCorrectly() {
    // given
    PersonCsv csvPerson = ObjectMother.getPersonCsvBuilderWithId(1L)
        .zipcodeAndCity(null).build();
    
    // when
    PersonEty person = this.mapper.mapToEntity(csvPerson);

    // then
    assertThat(person.getZipcode()).isNull();
    assertThat(person.getCity()).isEmpty();
  }
}
