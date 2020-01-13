package com.example.colors.dao.impl.csv.parser;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCsv {

  private Long id;

  @CsvBindByPosition(position = 0)
  private String name;

  @CsvBindByPosition(position = 1)
  private String lastname;

  @CsvBindByPosition(position = 2)
  private String zipcodeAndCity;

  @CsvBindByPosition(position = 3)
  private Integer color;

  public String getCsvStringToWrite() {
    return name + ", " + lastname + ", " + zipcodeAndCity + ", " + color;
  }
}
