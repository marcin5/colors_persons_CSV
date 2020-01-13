package com.example.colors.dao.impl.csv.parser;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;
import com.google.common.base.CharMatcher;

@Component
public class CsvPersonMapper {

  public List<PersonEty> mapToEntity(List<PersonCsv> csvPersons) {
    return csvPersons.stream()
        .map(this::mapToEntity)
        .collect(Collectors.toList());
  }

  public PersonEty mapToEntity(PersonCsv csvPerson) {
    return PersonEty.builder()
        .id(csvPerson.getId())
        .name(csvPerson.getName())
        .lastname(csvPerson.getLastname())
        .zipcode(getZipcode(csvPerson))
        .city(getCity(csvPerson))
        .color(Color.getEnumFromValue(csvPerson.getColor()))
        .build();
  }

  private Long getZipcode(PersonCsv csvPerson) {
    try {
      String zipcode = CharMatcher.inRange('0', '9').retainFrom(csvPerson.getZipcodeAndCity());
      return Long.valueOf(zipcode);
    } catch (Exception e) {
      return null;
    }
  }

  private String getCity(PersonCsv csvPerson) {
    try {
      return csvPerson.getZipcodeAndCity().replaceAll("\\d", "").trim();
    } catch (Exception e) {
      return "";
    }
  }

  public PersonCsv mapToCsv(PersonEty person) {
    return PersonCsv.builder()
        .id(person.getId())
        .name(person.getName())
        .lastname(person.getLastname())
        .zipcodeAndCity("" + person.getZipcode() + " " + person.getCity())
        .color(person.getColor().getValue())
        .build();
  }

}
