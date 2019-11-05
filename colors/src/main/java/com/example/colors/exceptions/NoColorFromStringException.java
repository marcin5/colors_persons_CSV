package com.example.colors.exceptions;

public class NoColorFromStringException extends BusinessException {

  private static final long serialVersionUID = 1L;
  public static final String MESSAGE = "No color found for string: ";

  public NoColorFromStringException(String color) {
    super(MESSAGE + color);
  }
}
