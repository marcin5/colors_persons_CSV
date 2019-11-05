package com.example.colors;

import java.util.Arrays;
import java.util.List;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
import com.example.colors.dao.impl.csv.parser.PersonCsv.PersonCsvBuilder;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;
import com.example.colors.model.entity.PersonEty.PersonEtyBuilder;
import com.example.colors.model.to.PersonTO;
import com.example.colors.model.to.PersonTO.PersonTOBuilder;

public class ObjectMother {

  public static PersonCsv getPersonCsvWithId(Long id) {
    return getPersonCsvBuilderWithId(id).build();
  }

  public static PersonCsvBuilder getPersonCsvBuilderWithId(Long id) {
    return PersonCsv.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcodeAndCity("50345 Wroclaw")
        .color(1);
  }

  public static PersonTO getPersonTOWithId(Long id) {
    return getPersonTOBuilderWithId(id).build();
  }

  public static PersonTOBuilder getPersonTOBuilderWithId(Long id) {
    return PersonTO.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcode(32456L)
        .city("Wroclaw")
        .color(Color.BLUE);
  }

  public static PersonEty getPersonEtyWithId(Long id) {
    return getPersonEtyBuilderWithId(id).build();
  }

  public static PersonEtyBuilder getPersonEtyBuilderWithId(Long id) {
    return PersonEty.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcode(32456L)
        .city("Wroclaw")
        .color(Color.BLUE);
  }

  public static List<PersonEty> getRandomPersons() {
    return Arrays.asList(getPersonEtyWithId(1L), getPersonEtyWithId(2L));
  }

}
