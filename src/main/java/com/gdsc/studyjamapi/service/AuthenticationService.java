package com.gdsc.studyjamapi.service;

import com.gdsc.studyjamapi.dto.request.SignInRequest;
import com.gdsc.studyjamapi.dto.response.SignInResponse;
import com.gdsc.studyjamapi.entity.User;
import com.gdsc.studyjamapi.repository.UserRepository;
import com.gdsc.studyjamapi.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  public SignInResponse signIn(SignInRequest request) {
    var authenticationToken =
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    authenticationManager.authenticate(authenticationToken);
    User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
    String jwt = JwtUtils.generateToken(user);
    return SignInResponse.builder().token(jwt).build();
  }
}
