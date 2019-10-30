package com.example.colors;

import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;
import com.example.colors.model.PersonDTO.PersonDTOBuilder;

public class ObjectMother {
  
  public static PersonDTO getPersonWithId(Long id) {
    return getPersonBuilderWithId(id).build();
  }

  public static PersonDTOBuilder getPersonBuilderWithId(Long id) {
    return PersonDTO.builder()
        .id(id)
        .name("Patrick")
        .lastname("Big")
        .zipcode(1L)
        .city("Wroclaw")
        .color(Color.BLUE);
  }

}
