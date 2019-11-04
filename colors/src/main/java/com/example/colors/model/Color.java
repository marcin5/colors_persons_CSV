package com.example.colors.model;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Color {
  BLUE(1), GREEN(2), PURPLE(3), RED(4), YELLOW(5), TURQUOIS(6), WHITE(7);

  @Getter
  private final int value;

  private Color(int value) {
    this.value = value;
  }

  @JsonValue
  public String toJson() {
    return name().toLowerCase();
  }

  public static Color getEnumFromValue(Integer value) {
    if (value == null) {
      return null;
    }

    return Arrays.stream(values())
        .filter(c -> c.getValue() == value)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
