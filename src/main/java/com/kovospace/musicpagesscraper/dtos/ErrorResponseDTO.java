package com.kovospace.musicpagesscraper.dtos;

public class ErrorResponseDTO {
  private String error;

  public ErrorResponseDTO(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }
}
