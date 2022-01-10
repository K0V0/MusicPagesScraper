package com.kovospace.musicpagesscraper.controllers;

import com.kovospace.musicpagesscraper.dtos.ErrorResponseDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainControllerAdvice {

  @ExceptionHandler(PageException.class)
  public ResponseEntity<ErrorResponseDTO> handlePageExceptions(PageException e) {
    switch (e.getClass().getSimpleName()) {
      case "PageNotFoundException":
        return response(e, 404);
      default:
        return response(e, 500);
    }
  }

  @ExceptionHandler(FactoryException.class)
  public ResponseEntity<ErrorResponseDTO> handleFactoryExceptions(FactoryException e) {
    switch (e.getClass().getSimpleName()) {
      case "NoPlatformException":
        return response(e, 404);
      default:
        return response(e, 500);
    }
  }

  private ResponseEntity<ErrorResponseDTO> response(Exception e, int httpStatusCode) {
    return ResponseEntity
      .status(httpStatusCode)
      .body(new ErrorResponseDTO(e.getMessage()));
  }
}
