package com.example.colors.dao.impl.csv;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.colors.dao.PersonDAO;
import com.example.colors.dao.impl.csv.parser.CsvPersonMapper;
import com.example.colors.dao.impl.csv.parser.CsvPersonParser;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
import com.example.colors.model.Color;
import com.example.colors.model.entity.PersonEty;

@Primary
@Component("CsvDao")
public class CsvPersonDao implements PersonDAO<PersonEty> {
  
  @Value("${csv.path}")
  private String dataSourcePath;

  private CsvPersonMapper csvMapper;
  private CsvPersonParser csvParser;

  @Autowired
  public CsvPersonDao(CsvPersonMapper csvMapper, CsvPersonParser csvParser) {
    this.csvMapper = csvMapper;
    this.csvParser = csvParser;
  }

  @Override
  public Optional<PersonEty> findById(long id) {
    List<PersonCsv> csvPersons = this.csvParser.getAllFromFile(dataSourcePath);

    Optional<PersonCsv> csvPerson = csvPersons.stream()
        .filter(person -> person.getId() == id)
        .findFirst();

    if (csvPerson.isPresent()) {
      return Optional.of(this.csvMapper.mapToEntity(csvPerson.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<PersonEty> findAll() {
    List<PersonCsv> csvPersons = this.csvParser.getAllFromFile(dataSourcePath);
    return csvMapper.mapToEntity(csvPersons);
  }

  @Override
  public List<PersonEty> findByColor(Color color) {
    List<PersonCsv> csvPersons = this.csvParser.getAllFromFile(dataSourcePath);

    List<PersonCsv> csvPersonsWithColor = csvPersons.stream()
        .filter(person -> person.getColor() == color.getValue())
        .collect(Collectors.toList());

    return this.csvMapper.mapToEntity(csvPersonsWithColor);
  }

  @Override
  public PersonEty save(PersonEty t) {
    PersonCsv csvPerson = this.csvMapper.mapToCsv(t);
    this.csvParser.addToFile(csvPerson, dataSourcePath);
    return t;
  }

}
