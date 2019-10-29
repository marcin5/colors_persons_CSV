package com.example.colors.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.example.colors.model.Color;
import lombok.Data;

@Entity
@Data
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  private String lastname;
  private long zipcode;
  private String city;
  
  @Enumerated(EnumType.ORDINAL)
  private Color color;
}
