package com.gdsc.studyjamapi.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private UUID id;
    private String title;
    private List<String> requirement;
    private String description;
    private Timestamp deadline;
    private String eventId;
}
