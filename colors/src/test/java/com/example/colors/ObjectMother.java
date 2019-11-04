package com.example.colors;

import java.util.Arrays;
import java.util.List;
import com.example.colors.entity.Person;
import com.example.colors.entity.Person.PersonBuilder;
import com.example.colors.model.Color;
import com.example.colors.model.PersonTO;
import com.example.colors.model.PersonTO.PersonTOBuilder;

public class ObjectMother {
  
  public static PersonTO getPersonTOWithId(Long id) {
    return getPersonTOBuilderWithId(id).build();
  }

  public static  PersonTOBuilder getPersonTOBuilderWithId(Long id) {
    return PersonTO.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcode(1L)
        .city("Wroclaw")
        .color(Color.BLUE);
  }
  
  public static Person getPersonWithId(Long id) {
    return getPersonBuilderWithId(id).build();
  }

  public static PersonBuilder getPersonBuilderWithId(Long id) {
    return Person.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcode(1L)
        .city("Wroclaw")
        .color(Color.BLUE);
  }
  
  public static List<Person> getRandomPersons() {
    return Arrays.asList(getPersonWithId(1L), getPersonWithId(2L));
  }

}
