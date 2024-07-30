package com.gdsc.studyjamapi.service;

import com.gdsc.studyjamapi.dto.request.SignInRequest;
import com.gdsc.studyjamapi.dto.response.SignInResponse;
import com.gdsc.studyjamapi.entity.User;
import com.gdsc.studyjamapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTests {
  @MockBean private AuthenticationManager authenticationManager;
  @MockBean private UserRepository userRepository;
  @Autowired private AuthenticationService authenticationService;

  @Test
  void signIn_giveExactCredentials_returnOk() {
    String email = "test@gmail.com";
    SignInRequest request = SignInRequest.builder().email(email).password("password").build();
    var authenticationToken =
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    User expectedUser = User.builder().email(email).password("encodedPassword").build();

    when(authenticationManager.authenticate(authenticationToken))
        .thenReturn(
            new UsernamePasswordAuthenticationToken(
                expectedUser.getEmail(), expectedUser.getPassword()));
    when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(expectedUser));
    SignInResponse actualResponse = authenticationService.signIn(request);

    assertNotNull(actualResponse);
    assertNotNull(actualResponse.getToken());
  }

  @Test
  void signIn_giveBadCredentials_throwsException() {
    SignInRequest request =
        SignInRequest.builder().email("wrong-email@gmail.com").password("password").build();
    var authenticationToken =
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    when(authenticationManager.authenticate(authenticationToken))
        .thenThrow(new BadCredentialsException("Bad credentials"));
    var exception =
        assertThrows(BadCredentialsException.class, () -> authenticationService.signIn(request));
    assertEquals("Bad credentials", exception.getMessage());
  }
}
