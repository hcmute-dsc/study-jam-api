package com.gdsc.studyjamapi.api;

import com.gdsc.studyjamapi.dto.request.CreateUserRequest;
import com.gdsc.studyjamapi.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/users")
public interface UserApi {
  @PostMapping
  ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request, UriComponentsBuilder ucb);

  @GetMapping
  ResponseEntity<List<UserResponse>> getAllUsers();

  @GetMapping("/{userId}")
  ResponseEntity<UserResponse> getUserById(@PathVariable String userId);

  @GetMapping("/current")
  ResponseEntity<UserResponse> getCurrentUser();
}
