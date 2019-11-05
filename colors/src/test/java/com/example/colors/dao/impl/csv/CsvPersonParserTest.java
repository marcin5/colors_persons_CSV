package com.example.colors.dao.impl.csv;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.example.colors.dao.impl.csv.parser.CsvPersonParser;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
import com.example.colors.exceptions.CsvParserException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@SpringBootTest
//@TestPropertySource(properties = "csv.path=src/test/resources/testPersons.csv")
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
    assertThat(persons.size()).isEqualTo(2);
  }

  @Test
  public void shouldAddOneRecord() {
    // given
    List<PersonCsv> personsBefore = this.parser.getAllFromFile(dataSourcePath);
    assertThat(personsBefore.size()).isEqualTo(2);

    PersonCsv person = ObjectMother.getPersonCsvWithId(1L);

    // when
    this.parser.addToFile(person, dataSourcePath);

    // then
    List<PersonCsv> personsAfter = this.parser.getAllFromFile(dataSourcePath);
    assertThat(personsAfter.size()).isEqualTo(3);
  }
  
  @Test
  public void shouldThrowBussinesException() {
    // given
    PersonCsv person = new PersonCsv();

    // when //then
    Assertions.assertThrows(CsvParserException.class, () -> {
      this.parser.addToFile(person, "fakePath/fake.csv");
    });
  }
}
