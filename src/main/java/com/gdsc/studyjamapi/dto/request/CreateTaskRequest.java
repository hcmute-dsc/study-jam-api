package com.gdsc.studyjamapi.dto.request;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.*;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    @NotBlank(message = REQUIRED_TITLE)
    private String title;

    @NotBlank(message = REQUIRED_DESCRIPTION)
    private String description;

    @NotEmpty(message = REQUIRED_REQUIREMENT)
    private List<String> requirement;

    @NotNull(message = REQUIRED_DEADLINE)
    private Timestamp deadline;

    @NotBlank(message = REQUIRED_EVENT_ID)
    private String eventId;
}
