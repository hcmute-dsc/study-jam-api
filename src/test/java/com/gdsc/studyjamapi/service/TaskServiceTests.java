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
                        .requirement("Task Requirement")
                        .note("Task Note")
                        .submission("Task Submission")
                        .eventId("123")
                        .build();

        Task newTaskRequest =
                Task.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .requirement(request.getRequirement())
                        .note(request.getNote())
                        .submission(request.getSubmission())
                        .eventId(request.getEventId())
                        .build();

        Task expectedTask =
                Task.builder()
                        .id(UUID.fromString("7ce10103-45af-4569-90ea-213306d05ec1"))
                        .title(newTaskRequest.getTitle())
                        .description(newTaskRequest.getDescription())
                        .requirement(newTaskRequest.getRequirement())
                        .note(newTaskRequest.getNote())
                        .submission(newTaskRequest.getSubmission())
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
        assertEquals(expectedTask.getNote(), actualResponse.getNote());
        assertEquals(expectedTask.getSubmission(), actualResponse.getSubmission());
        assertEquals(expectedTask.getEventId(), actualResponse.getEventId());
    }
    @Test
    void createTask_eventNotExists_throwsRemoteEntityDoesNotExist() {
        CreateTaskRequest request =
                CreateTaskRequest.builder()
                        .title("Task Title")
                        .description("Task Description")
                        .requirement("Task Requirement")
                        .note("Task Note")
                        .submission("Task Submission")
                        .eventId("456")
                        .build();
        when(eventService.doesEventExist(request.getEventId())).thenReturn(false);
        RemoteEntityDoesNotExist exception =
                assertThrows(RemoteEntityDoesNotExist.class, () -> taskService.createTask(request));
        assertEquals(exception.getMessage(), String.format(NOT_FOUND_MESSAGE_TEMPLATE, "Event"));
    }
}
