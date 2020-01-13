package com.example.colors.exceptions;

public class CsvParserException extends BusinessException {

  private static final long serialVersionUID = 1L;
  public static final String MESSAGE = "An error occurred while connecting to database server";

  public CsvParserException() {
    super(MESSAGE);
  }
}
