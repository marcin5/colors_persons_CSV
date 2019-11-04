package com.example.colors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonTO {
  private Long id;
  private String name;
  private String lastname;
  private long zipcode;
  private String city;
  private Color color;
}
