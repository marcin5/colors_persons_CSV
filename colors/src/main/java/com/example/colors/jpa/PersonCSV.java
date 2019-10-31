package com.example.colors.jpa;

import com.example.colors.model.Color;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonCSV {
  
  private long id;

  @CsvBindByPosition(position = 0)
  private String name;
  
  @CsvBindByPosition(position = 1)
  private String lastname;

  @CsvBindByPosition(position = 2)
  private long zipcode;  

  @CsvBindByPosition(position = 3)
  private String city;
  
  @CsvBindByPosition(position = 4)
  private Color color;
}
