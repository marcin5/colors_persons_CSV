package com.example.colors.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.colors.entity.Person;

@RestController
@RequestMapping("/persons")
public class GetPerson {

  
  @GetMapping
  public List<Person> getPersons() {
    
    
      return null;
  }
  
  @GetMapping("/{id}")
  public Person getPerson() {
    
    
      return null;
  }
  
  @GetMapping("/color/{color}")
  public List<Person> getColorForPerson() {
    
    
      return null;
  }
  
  
}
