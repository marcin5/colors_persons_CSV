package com.example.colors.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.colors.exceptions.BusinessException;
import com.example.colors.jpa.Person;
import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;
import com.example.colors.repository.PersonRepository;

@Service
public class PersonService {
  private PersonRepository personRepository;
  private Mapper mapper;

  @Autowired
  public PersonService(PersonRepository personRepository, Mapper mapper) {
    this.personRepository = personRepository;
    this.mapper = mapper;
  }

  public PersonDTO getPersonById(long personId) {
    Optional<Person> person = this.personRepository.findById(personId);
    if (person.isPresent()) {
      return mapper.convertToDto(person.get());
    }
    throw new BusinessException("No Person found for id: " + personId);
  }

  public List<PersonDTO> getAllPersons() {
    List<Person> persons = this.personRepository.findAll();
    return mapper.convertToDto(persons);
  }

  public List<PersonDTO> getAllByColor(Color color) {
    List<Person> persons = this.personRepository.findByColor(color);
    return mapper.convertToDto(persons);
  }

  public void addPerson(PersonDTO person) {
    Person personEty = mapper.convertToEntity(person);
    this.personRepository.save(personEty);
  }

}
