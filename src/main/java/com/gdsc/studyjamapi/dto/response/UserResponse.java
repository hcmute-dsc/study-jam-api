package com.gdsc.studyjamapi.dto.response;

import com.gdsc.studyjamapi.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
  private UUID id;
  private String fullName;
  private String email;
  private Role role;
  private Boolean isEnabled;
}
