package com.example.colors.dao.impl;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.colors.dao.PersonDAO;
import com.example.colors.jpa.Person;
import com.example.colors.model.Color;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Primary
@Component("csv")
public class CSVDao implements PersonDAO<Person> {

  private static final String DATA_SOURCE_PATH = "src/main/resources/persons.csv";

  @Override
  public Optional<Person> findById(long id) {


    return null;
  }

  @Override
  public List<Person> findAll() {
    readAllDataAtOnce();
    return null;
  }

  @Override
  public List<Person> findByColor(Color color) {
    return null;
  }

  @Override
  public Person save(Person t) {
    return null;
  }

  public static List<Person> readAllDataAtOnce() {
    List<Person> persons = null;
    try (Reader reader = Files.newBufferedReader(Paths.get(DATA_SOURCE_PATH))) {     
      CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
          .withType(Person.class)
          .build();
      
      persons = csvToBean.parse();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return persons;
  }

}
