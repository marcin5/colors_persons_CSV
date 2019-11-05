package com.example.colors.dao.impl.csv.parser;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;
import com.example.colors.exceptions.CsvParserException;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvPersonParser {

  public List<PersonCsv> getAllFromFile(String dataSourcePath) {
    try (Reader reader = Files.newBufferedReader(Paths.get(dataSourcePath))) {
      return readAll(reader);

    } catch (Exception e) {
      throw new CsvParserException();
    }
  }

  public void addToFile(PersonCsv person, String dataSourcePath) {
    try (CSVWriter writer = new CSVWriter(new FileWriter(dataSourcePath, true), ',',
        ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)) {

      String[] record = person.getCsvStringToWrite().split(",");
      writer.writeNext(record);

    } catch (Exception e) {
      throw new CsvParserException();
    }
  }

  private List<PersonCsv> readAll(Reader reader) {
    CsvToBean<PersonCsv> csvToBean = new CsvToBeanBuilder<PersonCsv>(reader)
        .withType(PersonCsv.class)
        .build();

    List<PersonCsv> records = csvToBean.parse();
    addIdsToPersons(records);
    return records;
  }

  private void addIdsToPersons(List<PersonCsv> persons) {
    AtomicLong id = new AtomicLong(1L);
    persons.stream().forEach(p -> p.setId(id.getAndIncrement()));
  }
}
