package com.gdsc.studyjamapi.service;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.EVENT_EXISTED;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.TaskResponse;
import com.gdsc.studyjamapi.entity.Event;
import com.gdsc.studyjamapi.entity.Task;
import com.gdsc.studyjamapi.exception.EntityNotExistedException;
import com.gdsc.studyjamapi.mapper.TaskMapper;
import com.gdsc.studyjamapi.repository.EventRepository;
import com.gdsc.studyjamapi.repository.TaskRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
  private TaskRepository taskRepository;
  private EventRepository eventRepository;

  public TaskResponse creatTask(CreateTaskRequest request) {
    Optional<Event> optionalEvent = eventRepository.findById(request.getEventId());
    if (!optionalEvent.isPresent()) {
      throw new EntityNotExistedException(EVENT_EXISTED);
    }
    Task newTaskRequestToNewTask = TaskMapper.INSTANCE.taskRequestToTask(request);
    Task newTaskRequest =
        Task.builder()
            .title(newTaskRequestToNewTask.getTitle())
            .description(newTaskRequestToNewTask.getDescription())
            .requirement(newTaskRequestToNewTask.getRequirement())
            .note(newTaskRequestToNewTask.getNote())
            .submission(newTaskRequestToNewTask.getSubmission())
            .eventId(newTaskRequestToNewTask.getEventId())
            .build();
    Task newTask = taskRepository.save(newTaskRequest);
    return TaskMapper.INSTANCE.taskToTaskResponse(newTask);
  }
}
