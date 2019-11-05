package com.example.colors.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.colors.dao.PersonDAO;
import com.example.colors.exceptions.NoPersonFoundException;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;
import com.example.colors.model.to.PersonTO;

@Service
public class PersonService {

  private PersonDAO<PersonEty> personDao;
  private ModelMapper mapper;

  @Autowired
  public PersonService(PersonDAO<PersonEty> personDao, ModelMapper mapper) {
    this.personDao = personDao;
    this.mapper = mapper;
  }

  public List<PersonTO> getAllPersons() {
    List<PersonEty> persons = this.personDao.findAll();
    return convertToDto(persons);
  }

  public PersonTO getPersonById(long personId) {
    Optional<PersonEty> person = this.personDao.findById(personId);
    if (person.isPresent()) {
      return this.mapper.map(person.get(), PersonTO.class);
    }
    throw new NoPersonFoundException(personId);
  }

  public List<PersonTO> getAllByColor(Color color) {
    List<PersonEty> persons = this.personDao.findByColor(color);
    return convertToDto(persons);
  }

  public void addPerson(PersonTO person) {
    PersonEty personEty = this.mapper.map(person, PersonEty.class);
    this.personDao.save(personEty);
  }

  private List<PersonTO> convertToDto(List<PersonEty> persons) {
    return persons.stream()
        .map(entity -> mapper.map(entity, PersonTO.class))
        .collect(Collectors.toList());
  }

}
