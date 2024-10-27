package com.gdsc.studyjamapi.dto.request;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.*;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
  @NotBlank(message = REQUIRED_TITLE)
  private String title;

  @NotBlank(message = REQUIRED_DESCRIPTION)
  private List<String> description;

  @NotBlank(message = REQUIRED_REQUIREMENT)
  private List<String> requirement;

  private List<String> note;
  private List<String> submission;
  private UUID eventId;
}
