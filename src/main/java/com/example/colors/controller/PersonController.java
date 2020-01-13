package com.example.colors.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.colors.exceptions.NoColorFromStringException;
import com.example.colors.model.Color;
import com.example.colors.model.to.PersonTO;
import com.example.colors.service.PersonService;

@RestController
public class PersonController {
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping("/persons")
  public void addPerson(@RequestBody PersonTO person) {
    this.personService.addPerson(person);
  }

  @GetMapping("/persons")
  public List<PersonTO> getAllPersons() {
    return personService.getAllPersons();
  }

  @GetMapping("/persons/{id}")
  public PersonTO getPerson(@PathVariable long id) {
    return personService.getPersonById(id);
  }

  @GetMapping("/persons/color/{color}")
  public List<PersonTO> getAllPersonsForColor(@PathVariable String color) {
    return personService.getAllByColor(getColorFromString(color));
  }

  private Color getColorFromString(String requestColor) {
    try {
      return Color.valueOf(requestColor.toUpperCase());
    } catch (Exception e) {
      throw new NoColorFromStringException(requestColor);
    }
  }

}
