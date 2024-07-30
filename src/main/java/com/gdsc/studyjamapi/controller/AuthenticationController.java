package com.gdsc.studyjamapi.controller;

import com.gdsc.studyjamapi.api.AuthenticationApi;
import com.gdsc.studyjamapi.dto.request.SignInRequest;
import com.gdsc.studyjamapi.dto.response.SignInResponse;
import com.gdsc.studyjamapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {
  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<SignInResponse> signIn(SignInRequest request) {
    return ResponseEntity.ok(authenticationService.signIn(request));
  }
}
