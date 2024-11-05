package com.gdsc.studyjamapi.dto.response;

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
public class TaskResponse {
    private UUID id;
    private String title;
    private String requirement;
    private String note;
    private String submission;
    private String description;
    private String eventId;
}
