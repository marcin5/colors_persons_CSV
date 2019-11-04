package com.example.colors.dao.impl.csv;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.colors.exceptions.CsvException;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvParser {

  @Value("${userBucket.path}")
  private String dataSourcePath;

  public List<CsvPerson> getAll() {
    try (Reader reader = Files.newBufferedReader(Paths.get(dataSourcePath))) {
      return readAll(reader);

    } catch (Exception e) {
      throw new CsvException();
    }
  }

  public void add(CsvPerson person) {
    try (CSVWriter writer = new CSVWriter(new FileWriter(dataSourcePath, true), ',',
        ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER, ICSVWriter.DEFAULT_LINE_END)) {

      String[] record = person.getCsvStringToWrite().split(",");
      writer.writeNext(record);

    } catch (Exception e) {
      throw new CsvException();
    }
  }

  private List<CsvPerson> readAll(Reader reader) {
    CsvToBean<CsvPerson> csvToBean = new CsvToBeanBuilder<CsvPerson>(reader)
        .withType(CsvPerson.class)
        .build();

    List<CsvPerson> persons = csvToBean.parse();
    AtomicLong id = new AtomicLong(1L);
    persons.stream().forEach(p -> p.setId(id.getAndIncrement()));

    return persons;
  }
}
