package com.example.colors.exceptions;

public class NoPersonFoundException extends BusinessException {
  
  private static final long serialVersionUID = 1L;
  public static final String MESSAGE = "No Person found for id: ";

  public NoPersonFoundException(long personId) {
    super(MESSAGE + personId);
  }
}
