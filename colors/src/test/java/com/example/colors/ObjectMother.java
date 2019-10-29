package com.example.colors;

import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;

public class ObjectMother {

  public static PersonDTO getDefaultPerson() {
    return PersonDTO.builder()
        .id(1L)
        .name("Patrick")
        .lastname("Big")
        .zipcode(1L)
        .city("Wroclaw")
        .color(Color.BLUE).build();
  }


}
