package com.example.colors.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Color {
  BLUE(1), GREEN(2), PURPLE(3), RED(4), YELLOW(5), TURQUOIS(6), WHITE(7);

  public final int value;

  private Color(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @JsonValue
  public String toJson() {
    return name().toLowerCase();
  }

}
