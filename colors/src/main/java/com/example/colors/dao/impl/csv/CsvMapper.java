package com.example.colors.dao.impl.csv;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.colors.entity.Person;
import com.example.colors.model.Color;

@Component
public class CsvMapper {

  public List<Person> mapToEntity(List<CsvPerson> csvPersons) {
    return csvPersons.stream()
        .map(this::mapToEntity)
        .collect(Collectors.toList());
  }
  
  public Person mapToEntity(CsvPerson csvPerson) {
    String zipcode = csvPerson.getZipcodeAndCity().trim().substring(0, 5);
    String city = csvPerson.getZipcodeAndCity().trim().substring(5);
    
    return Person.builder()
        .id(csvPerson.getId())
        .name(csvPerson.getName())
        .lastname(csvPerson.getLastname())
        .zipcode(Long.valueOf(zipcode))
        .city(city)
        .color(Color.getEnumFromValue(csvPerson.getColor()))
        .build();
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
