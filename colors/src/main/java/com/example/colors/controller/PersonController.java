package com.example.colors.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;
import com.example.colors.service.PersonService;

@RestController
public class PersonController {
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }
  
  @PostMapping("/persons")
  public void addPerson(@RequestBody PersonDTO person) {
    this.personService.addPerson(person);
  }

  @GetMapping("/persons")
  public List<PersonDTO> getAllPersons() {
    return personService.getAllPersons();
  }
 
  @GetMapping("/persons/{id}")
  public PersonDTO getPerson(@PathVariable long id) {
    return personService.getPersonById(id);
  }

  @GetMapping("/persons/color/{color}")
  public List<PersonDTO> getAllPersonsForColor(@PathVariable String color) {
    return personService.getAllByColor(Color.valueOf(color.toUpperCase()));
  }

}
