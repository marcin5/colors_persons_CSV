package com.example.colors.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.colors.dao.PersonDAO;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.jpa.Person;
import com.example.colors.model.Color;
import com.example.colors.model.PersonDTO;

@Service
public class PersonService {

  private PersonDAO<Person> personDao;
  private ModelMapper mapper;

  @Autowired
  public PersonService(PersonDAO<Person> personDao, ModelMapper mapper) {
    this.personDao = personDao;
    this.mapper = mapper;
  }

  public List<PersonDTO> getAllPersons() {
    List<Person> persons = this.personDao.findAll();
    return convertToDto(persons);
  }

  public PersonDTO getPersonById(long personId) {
    Optional<Person> person = this.personDao.findById(personId);
    if (person.isPresent()) {
      return this.mapper.map(person.get(), PersonDTO.class);
    }
    throw new NoPersonFoundException(personId);
  }

  public List<PersonDTO> getAllByColor(Color color) {
    List<Person> persons = this.personDao.findByColor(color);
    return convertToDto(persons);
  }

  public void addPerson(PersonDTO person) {
    Person personEty = this.mapper.map(person, Person.class);
    this.personDao.save(personEty);
  }

  private List<PersonDTO> convertToDto(List<Person> persons) {
    return persons.stream()
        .map(entity -> mapper.map(entity, PersonDTO.class))
        .collect(Collectors.toList());
  }

}
