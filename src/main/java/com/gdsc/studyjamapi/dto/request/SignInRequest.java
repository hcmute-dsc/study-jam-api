package com.gdsc.studyjamapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.REQUIRED_EMAIL;
import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.REQUIRED_PASSWORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
  @NotBlank(message = REQUIRED_EMAIL)
  private String email;

  @NotBlank(message = REQUIRED_PASSWORD)
  private String password;
}
