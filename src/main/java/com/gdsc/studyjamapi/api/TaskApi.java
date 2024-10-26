package com.gdsc.studyjamapi.api;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.ErrorResponse;
import com.gdsc.studyjamapi.dto.response.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/tasks")
@Tag(name = "Task")
public interface TaskApi {
  @Operation(summary = "Create a new task")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Task created successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema)}),
        @ApiResponse(
            responseCode = "400",
            description = "Event is not existed",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class))
            })
      })
  @PostMapping
  ResponseEntity<TaskResponse> createTask(
      @RequestBody @Valid CreateTaskRequest request, UriComponentsBuilder ucb);
}
