package com.example.colors.dao.impl.csv.parser;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.colors.ObjectMother;
import com.example.colors.TestHelper;
import com.example.colors.exceptions.CsvParserException;

@SpringBootTest
public class CsvPersonParserTest {

  private static final String DATA_SOURCE_PATH = "src/test/resources/testPersons.csv";

  @Autowired
  private CsvPersonParser parser;

  @BeforeEach
  public void init() {
    TestHelper.initDataInTestPersonsCsvFile(ObjectMother.getDataSetExample1());
  }

  @Test
  public void shouldGetAllRecords() {
    // when
    List<PersonCsv> persons = this.parser.getAllFromFile(DATA_SOURCE_PATH);

    // then
    assertEquals(3, persons.size());
  }

  @Test
  public void shouldAddOneRecord() {
    // given
    List<PersonCsv> personsBefore = this.parser.getAllFromFile(DATA_SOURCE_PATH);
    assertEquals(3, personsBefore.size());

    PersonCsv person = ObjectMother.getPersonCsvWithId(1L);

    // when
    this.parser.addToFile(person, DATA_SOURCE_PATH);

    // then
    List<PersonCsv> personsAfter = this.parser.getAllFromFile(DATA_SOURCE_PATH);
    assertEquals(4, personsAfter.size());
  }

  @Test
  public void shouldThrowBussinesException() {
    // given // when //then
    Assertions.assertThrows(CsvParserException.class, () -> {
      this.parser.addToFile(new PersonCsv(), "fakePath/fake.csv");
    });
  }
}
