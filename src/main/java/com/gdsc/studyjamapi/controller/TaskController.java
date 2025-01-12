package com.gdsc.studyjamapi.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gdsc.studyjamapi.api.TaskApi;
import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.TaskResponse;
import com.gdsc.studyjamapi.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {
  private final TaskService taskService;

  @Override
  public ResponseEntity<TaskResponse> createTask(
      CreateTaskRequest request, UriComponentsBuilder ucb) {
    TaskResponse taskResponse = taskService.createTask(request);
    URI locationOfNewTask = ucb.path("/tasks/{id}").buildAndExpand(taskResponse.getId()).toUri();
    return ResponseEntity.created(locationOfNewTask).build();
  }
}
