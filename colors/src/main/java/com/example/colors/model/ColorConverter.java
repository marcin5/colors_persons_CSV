package com.example.colors.model;

import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

@Converter(autoApply = true)
public class ColorConverter extends AbstractBeanField<String, Integer> implements AttributeConverter<Color, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Color color) {
    if (color == null) {
      return null;
    }
    return color.getValue();
  }

  @Override
  public Color convertToEntityAttribute(Integer value) {
    return getEnumFromValue(value);
  }
  
  private Color getEnumFromValue(Integer value) {
    if (value == null) {
      return null;
    }
    
    return Arrays.stream(Color.values())
        .filter(c -> c.getValue() == value)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override  
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {  
       try {  
            return getEnumFromValue(Integer.valueOf(value.trim()));
       } catch (RuntimeException e) {  
            throw new CsvDataTypeMismatchException(e.getMessage());  
       }  
  }  
}
