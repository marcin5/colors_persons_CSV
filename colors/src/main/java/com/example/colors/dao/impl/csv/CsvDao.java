package com.example.colors.dao.impl.csv;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.colors.dao.PersonDAO;
import com.example.colors.entity.Person;
import com.example.colors.model.Color;

@Primary
@Component("CsvDao")
public class CsvDao implements PersonDAO<Person> {

  private CsvMapper csvMapper;
  private CsvParser csvParser;

  @Autowired
  public CsvDao(CsvMapper csvMapper, CsvParser csvParser) {
    this.csvMapper = csvMapper;
    this.csvParser = csvParser;
  }

  @Override
  public Optional<Person> findById(long id) {
    List<CsvPerson> csvPersons = csvParser.getAll();
    
    Optional<CsvPerson> csvPerson =  csvPersons.stream()
        .filter(e -> e.getId() == id)
        .findFirst();
    
    if(csvPerson.isPresent()) {
      return Optional.of(csvMapper.mapToEntity(csvPerson.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<Person> findAll() {
    List<CsvPerson> csvPersons = csvParser.getAll();
    return csvMapper.mapToEntity(csvPersons);
  }

  @Override
  public List<Person> findByColor(Color color) {
    List<CsvPerson> csvPersons = csvParser.getAll();

    List<CsvPerson> csvPersonsWithColor = csvPersons.stream()
        .filter(e -> e.getColor() == color.getValue())
        .collect(Collectors.toList());

    return csvMapper.mapToEntity(csvPersonsWithColor);
  }

  @Override
  public Person save(Person t) {
    CsvPerson csvPerson = csvMapper.mapToCsv(t);
    csvParser.add(csvPerson);
    return t;
  }

}
