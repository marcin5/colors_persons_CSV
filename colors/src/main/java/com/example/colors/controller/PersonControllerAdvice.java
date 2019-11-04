package com.example.colors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.colors.exceptions.NoColorFromStringException;
import com.example.colors.exceptions.NoPersonFoundException;

@ControllerAdvice
public class PersonControllerAdvice {

  @ExceptionHandler(value = {NoPersonFoundException.class})
  public ResponseEntity<Object> handleError(NoPersonFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {NoColorFromStringException.class})
  public ResponseEntity<Object> handleError(NoColorFromStringException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Object> handleError(Exception ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
