package com.example.colors;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import com.example.colors.dao.impl.csv.parser.PersonCsv;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class TestHelper {

  private static final String DATA_SOURCE_PATH = "src/test/resources/testPersons.csv";

  public static void initDataInTestPersonsCsvFile(List<PersonCsv> persons) {
    try (Writer writer = Files.newBufferedWriter(Paths.get(DATA_SOURCE_PATH))) {
      StatefulBeanToCsv<PersonCsv> beanToCsv = new StatefulBeanToCsvBuilder<PersonCsv>(writer)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      beanToCsv.write(persons);
    } catch (Exception e) {
      Assert.fail("fail to load data to csv file");
    }
  }
}
