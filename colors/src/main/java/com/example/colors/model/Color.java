package com.example.colors.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
  BLUE(1), 
  GREEN(2), 
  PURPLE(3), 
  RED(4), 
  YELLOW(5), 
  TURQUOIS(6), 
  WHITE(7);
  
  public final int numberValue;
  
  private Color(int numberValue) {
      this.numberValue = numberValue;
  }
  
  @JsonValue
  public String toJson() {
    return name().toLowerCase();
  }
  
}
