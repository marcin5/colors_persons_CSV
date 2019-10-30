package com.example.colors.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.colors.jpa.Person;
import com.example.colors.model.PersonDTO;

@Component
public class Mapper {
  private ModelMapper modelMapper;
  
  @Autowired
  public Mapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
  
  public PersonDTO convertEtyToDto(Person person){
    return modelMapper.map(person, PersonDTO.class);
  }
  
  public List<PersonDTO> convertToDto(List<Person> persons){
    return persons.stream()
        .map(e -> modelMapper.map(e, PersonDTO.class))
        .collect(Collectors.toList());
  }
  
  public Person convertToEntity(PersonDTO person){
    return modelMapper.map(person, Person.class);
  }
  
}
