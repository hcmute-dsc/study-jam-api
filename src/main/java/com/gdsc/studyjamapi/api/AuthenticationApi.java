package com.gdsc.studyjamapi.api;

import com.gdsc.studyjamapi.dto.request.SignInRequest;
import com.gdsc.studyjamapi.dto.response.SignInResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthenticationApi {
  @PostMapping("/sign-in")
  ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest request);
}
