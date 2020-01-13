package com.example.colors.exceptions;

public class UpdateNotAvailableException extends BusinessException {

  private static final long serialVersionUID = 1L;
  public static final String MESSAGE = "Update is not available, try request without id: ";

  public UpdateNotAvailableException(long personId) {
    super(MESSAGE + personId);
  }
}
