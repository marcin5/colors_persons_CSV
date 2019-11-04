package com.example.colors.exceptions;

public class CsvException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public static final String MESSAGE = "An error occurred while connecting to database server";

  public CsvException() {
    super(MESSAGE);
  }
}
