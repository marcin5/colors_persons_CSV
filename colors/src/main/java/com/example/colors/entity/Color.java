package com.example.colors.entity;

public enum Color {
  BLUE(1), 
  GREEN(2), 
  PURPLE(3), 
  RED(4), 
  YELLOW(5), 
  TURQUOIS(6), 
  WHITE(7);
  
  public final int value;
  
  private Color(int value) {
      this.value = value;
  }
}
