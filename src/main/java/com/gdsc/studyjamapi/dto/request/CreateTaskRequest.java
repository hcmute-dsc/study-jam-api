package com.gdsc.studyjamapi.dto.request;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.*;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
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
    private String description;

    @NotBlank(message = REQUIRED_REQUIREMENT)
    private String requirement;

    private String note;
    private String submission;
    private String eventId;
}
