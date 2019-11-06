package com.example.colors.model.entity;

import com.example.colors.model.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonEty {

  private Long id;
  private String name;
  private String lastname;
  private Long zipcode;
  private String city;
  private Color color;
}
