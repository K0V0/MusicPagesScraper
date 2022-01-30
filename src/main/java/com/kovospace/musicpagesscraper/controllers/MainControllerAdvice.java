package com.kovospace.musicpagesscraper.controllers;

import com.kovospace.musicpagesscraper.dtos.ErrorResponseDTO;
import com.kovospace.musicpagesscraper.exceptions.FactoryException;
import com.kovospace.musicpagesscraper.exceptions.PageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MainControllerAdvice {

  @ExceptionHandler(PageException.class)
  public ResponseEntity<ErrorResponseDTO> handlePageExceptions(PageException e) {
    switch (e.getClass().getSimpleName()) {
      case "PageNotFoundException":
        return response(e, 404);
      case "PageScrapingException":
        return response(e, 501);
      default:
        return response(e, 500);
    }
  }

  @ExceptionHandler(FactoryException.class)
  public ResponseEntity<ErrorResponseDTO> handleFactoryExceptions(FactoryException e) {
    switch (e.getClass().getSimpleName()) {
      case "NoPlatformException":
        return response(e, 404);
      case "NoServiceException":
        return response(e, 503);
      default:
        return response(e, 500);
    }
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponseDTO> handleWrongArgumentTypeExceptions(MethodArgumentTypeMismatchException e) {
        return response(
                String.format("Wrong type for input parameter %s with value %s", e.getName(), e.getValue()), 400);
  }

  private ResponseEntity<ErrorResponseDTO> response(Exception e, int httpStatusCode) {
    return response(e.getMessage(), httpStatusCode);
  }

  private ResponseEntity<ErrorResponseDTO> response(String message, int httpStatusCode) {
    return ResponseEntity
            .status(httpStatusCode)
            .body(new ErrorResponseDTO(message));
  }

}
