package com.example.colors;

import java.util.Arrays;
import java.util.List;
import com.example.colors.jpa.Person;
import com.example.colors.jpa.Person.PersonBuilder;
import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;
import com.example.colors.model.PersonDTO.PersonDTOBuilder;

public class ObjectMother {
  
  public static PersonDTO getPersonDTOWithId(Long id) {
    return getPersonDTOBuilderWithId(id).build();
  }

  public static PersonDTOBuilder getPersonDTOBuilderWithId(Long id) {
    return PersonDTO.builder()
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
