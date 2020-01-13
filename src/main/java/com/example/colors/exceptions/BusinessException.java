package com.example.colors.exceptions;

public abstract class BusinessException extends RuntimeException{

  private static final long serialVersionUID = -9063115203677956504L;

  public BusinessException(String message) {
    super(message);
  }
}
