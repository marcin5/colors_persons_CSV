package com.example.colors.model.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.example.colors.model.Color;

@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Color, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Color color) {
    if (color == null) {
      return null;
    }
    return color.getValue();
  }

  @Override
  public Color convertToEntityAttribute(Integer value) {
    return Color.getEnumFromValue(value);
  }
}
