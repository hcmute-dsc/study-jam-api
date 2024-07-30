package com.gdsc.studyjamapi.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
  private UUID id = UUID.randomUUID();
  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }
}
