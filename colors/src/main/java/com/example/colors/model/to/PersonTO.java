package com.example.colors.model.to;

import com.example.colors.model.Color;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonTO {
  private Long id;
  private String name;
  private String lastname;
  private Long zipcode;
  private String city;
  private Color color;
  
  @JsonCreator
  public PersonTO(
      @JsonProperty(required = true)String name, 
      @JsonProperty(required = true)String lastname, 
      @JsonProperty(required = true)Long zipcode, 
      @JsonProperty(required = true)String city, 
      @JsonProperty(required = true)Color color) {
    this.name = name;
    this.lastname = lastname;
    this.zipcode = zipcode;
    this.city = city;
    this.color = color;
  }

}
