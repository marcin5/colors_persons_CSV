package com.example.colors.entity;

import lombok.Data;

@Data
public class Person {

  private int id;
  private String name;
  private String lastname;
  private long zipcode;
  private String city;
  private Color color;
  
}
