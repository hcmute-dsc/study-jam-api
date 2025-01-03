package com.gdsc.studyjamapi.service;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.TaskResponse;
import com.gdsc.studyjamapi.entity.Task;
import com.gdsc.studyjamapi.exception.RemoteEntityDoesNotExist;
import com.gdsc.studyjamapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.NOT_FOUND_MESSAGE_TEMPLATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
  @MockBean private TaskRepository taskRepository;
  @MockBean private EventService eventService;
  @Autowired private TaskService taskService;

  @Test
  void createTask_eventExists_createsTask() {
    CreateTaskRequest request =
        CreateTaskRequest.builder()
            .title("Task Title")
            .description("Task Description")
            .requirement(List.of("Requirement 1", "Requirement 2", "Requirement 3"))
            .startTime(LocalDateTime.now())
            .endTime(LocalDateTime.now().plusDays(1))
            .eventId("123")
            .build();

    Task newTaskRequest =
        Task.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .requirement(request.getRequirement())
            .eventId(request.getEventId())
            .startTime(request.getStartTime())
            .endTime(request.getEndTime())
            .build();

    Task expectedTask =
        Task.builder()
            .id(UUID.fromString("7ce10103-45af-4569-90ea-213306d05ec1"))
            .title(newTaskRequest.getTitle())
            .description(newTaskRequest.getDescription())
            .requirement(newTaskRequest.getRequirement())
            .startTime(newTaskRequest.getStartTime())
            .endTime(newTaskRequest.getEndTime())
            .eventId(newTaskRequest.getEventId())
            .build();

    when(eventService.doesEventExist(request.getEventId())).thenReturn(true);
    when(taskRepository.save(newTaskRequest)).thenReturn(expectedTask);

    TaskResponse actualResponse = taskService.createTask(request);

    assertNotNull(actualResponse);
    assertEquals(expectedTask.getId(), actualResponse.getId());
    assertEquals(expectedTask.getTitle(), actualResponse.getTitle());
    assertEquals(expectedTask.getDescription(), actualResponse.getDescription());
    assertEquals(expectedTask.getRequirement(), actualResponse.getRequirement());
    assertEquals(expectedTask.getStartTime(), actualResponse.getStartTime());
    assertEquals(expectedTask.getEndTime(), actualResponse.getEndTime());
    assertEquals(expectedTask.getEventId(), actualResponse.getEventId());
  }

  @Test
  void createTask_eventNotExists_throwsRemoteEntityDoesNotExist() {
    CreateTaskRequest request =
        CreateTaskRequest.builder()
            .title("Task Title")
            .description("Task Description")
            .requirement(List.of("Requirement 1", "Requirement 2", "Requirement 3"))
            .startTime(LocalDateTime.now())
            .endTime(LocalDateTime.now().plusDays(1))
            .eventId("456")
            .build();
    when(eventService.doesEventExist(request.getEventId())).thenReturn(false);
    RemoteEntityDoesNotExist exception =
        assertThrows(RemoteEntityDoesNotExist.class, () -> taskService.createTask(request));
    assertEquals(exception.getMessage(), String.format(NOT_FOUND_MESSAGE_TEMPLATE, "Event"));
  }
}
