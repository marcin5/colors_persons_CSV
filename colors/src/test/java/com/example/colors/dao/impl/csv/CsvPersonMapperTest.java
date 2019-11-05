package com.example.colors.dao.impl.csv;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.ObjectMother;
import com.example.colors.dao.impl.csv.parser.CsvPersonMapper;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
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
    assertThat(person.getId()).isEqualTo(1L);
    assertThat(person.getName()).isEqualTo("Patrick");
    assertThat(person.getLastname()).isEqualTo("Big");
    assertThat(person.getZipcode()).isEqualTo(50345L);
    assertThat(person.getCity()).isEqualTo("Wroclaw");
    assertThat(person.getColor()).isEqualTo(Color.BLUE);
  }
  
  @Test
  public void shouldMapEntityToPersonCsvObject() {
    // given
    PersonEty person = ObjectMother.getPersonEtyWithId(1L);
    
    // when
    PersonCsv personCsv = this.mapper.mapToCsv(person);

    // then
    assertThat(personCsv.getId()).isEqualTo(1L);
    assertThat(personCsv.getName()).isEqualTo("Patrick");
    assertThat(personCsv.getLastname()).isEqualTo("Big");
    assertThat(personCsv.getZipcodeAndCity()).isEqualTo("32456 Wroclaw");
    assertThat(personCsv.getColor()).isEqualTo(1);
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
    assertThat(person.getCity()).isNull();
  }
}
