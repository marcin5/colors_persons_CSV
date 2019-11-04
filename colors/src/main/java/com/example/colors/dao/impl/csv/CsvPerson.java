package com.example.colors.dao.impl.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CsvPerson {
  
  private long id;

  @CsvBindByPosition(position = 0)
  private String name;

  @CsvBindByPosition(position = 1)
  private String lastname;

  @CsvBindByPosition(position = 2)
  private String zipcodeAndCity;

  @CsvBindByPosition(position = 3)
  private int color;
  
  public String getCsvStringToWrite() {
    return "" + name + ", " + lastname + ", " + zipcodeAndCity + ", " + color;
  }
}
