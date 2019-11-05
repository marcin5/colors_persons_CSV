package com.example.colors.dao.impl.csv.parser;

import static org.junit.Assert.assertEquals;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.ObjectMother;
import com.example.colors.exceptions.CsvParserException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@SpringBootTest
public class CsvPersonParserTest {
  
  private String dataSourcePath = "src/test/resources/testPersons.csv";

  @Autowired
  private CsvPersonParser parser;

  @BeforeEach
  public void onSetUp() {
    try (Writer writer = Files.newBufferedWriter(Paths.get("src/test/resources/testPersons.csv"))) {
      StatefulBeanToCsv<PersonCsv> beanToCsv = new StatefulBeanToCsvBuilder<PersonCsv>(writer)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      List<PersonCsv> persons = Arrays.asList(ObjectMother.getPersonCsvWithId(1L), ObjectMother.getPersonCsvWithId(2L));
      beanToCsv.write(persons);
    } catch (Exception e) {
      Assert.fail("fail to load data to csv file");
    }
  }

  @Test
  public void shouldGetAllRecords() {
    // when
    List<PersonCsv> persons = this.parser.getAllFromFile(dataSourcePath);

    // then
    assertEquals(2, persons.size());
  }

  @Test
  public void shouldAddOneRecord() {
    // given
    List<PersonCsv> personsBefore = this.parser.getAllFromFile(dataSourcePath);
    assertEquals(2, personsBefore.size());

    PersonCsv person = ObjectMother.getPersonCsvWithId(1L);

    // when
    this.parser.addToFile(person, dataSourcePath);

    // then
    List<PersonCsv> personsAfter = this.parser.getAllFromFile(dataSourcePath);
    assertEquals(3, personsAfter.size());
  }
  
  @Test
  public void shouldThrowBussinesException() {
    // given // when //then
    Assertions.assertThrows(CsvParserException.class, () -> {
      this.parser.addToFile(new PersonCsv(), "fakePath/fake.csv");
    });
  }
}
