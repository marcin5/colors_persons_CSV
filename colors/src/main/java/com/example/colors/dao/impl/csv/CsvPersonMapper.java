package com.example.colors.dao.impl.csv;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.colors.model.Color;
import com.example.colors.model.entity.Person;

@Component
public class CsvPersonMapper {

  public List<Person> mapToEntity(List<CsvPerson> csvPersons) {
    return csvPersons.stream()
        .map(this::mapToEntity)
        .collect(Collectors.toList());
  }

  public Person mapToEntity(CsvPerson csvPerson) {
    return Person.builder()
        .id(csvPerson.getId())
        .name(csvPerson.getName())
        .lastname(csvPerson.getLastname())
        .zipcode(getZipcode(csvPerson))
        .city(getCity(csvPerson))
        .color(Color.getEnumFromValue(csvPerson.getColor()))
        .build();
  }

  private Long getZipcode(CsvPerson csvPerson) {
    try {
      String zipcode = csvPerson.getZipcodeAndCity().trim().substring(0, 5);
      return Long.valueOf(zipcode);
    } catch (Exception e) {
      return null;
    }
  }

  private String getCity(CsvPerson csvPerson) {
    try {
      return csvPerson.getZipcodeAndCity().trim().substring(5);
    } catch (Exception e) {
      return null;
    }
  }

  public CsvPerson mapToCsv(Person person) {
    return CsvPerson.builder()
        .name(person.getName())
        .lastname(person.getLastname())
        .zipcodeAndCity("" + person.getZipcode() + person.getCity())
        .color(person.getColor().getValue())
        .build();
  }

}
