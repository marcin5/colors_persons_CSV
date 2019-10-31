package com.example.colors.dao.impl;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.colors.dao.PersonDAO;
import com.example.colors.jpa.Person;
import com.example.colors.model.Color;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

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
    readAllDataAtOnce(DATA_SOURCE_PATH);
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

  public static void readAllDataAtOnce(String file) {
    try {
      FileReader filereader = new FileReader(file);

      CSVReader csvReader = new CSVReaderBuilder(filereader)
          .withSkipLines(1)
          .build();
      List<String[]> allData = csvReader.readAll();

      // print Data
      for (String[] row : allData) {
        for (String cell : row) {
          System.out.print(cell + "\t");
        }
        System.out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
