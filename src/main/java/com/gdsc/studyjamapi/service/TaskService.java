package com.gdsc.studyjamapi.service;

import static com.gdsc.studyjamapi.common.Constants.ErrorMessage.EVENT_NOT_FOUND;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.TaskResponse;
import com.gdsc.studyjamapi.entity.Event;
import com.gdsc.studyjamapi.entity.Task;
import com.gdsc.studyjamapi.exception.RemoteEntityDoesNotExist;
import com.gdsc.studyjamapi.mapper.TaskMapper;
import com.gdsc.studyjamapi.repository.TaskRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final EventService eventService;
    public TaskResponse creatTask(CreateTaskRequest request) {
        if (!this.eventService.isEventExist(request.getEventId())) {
            throw new RemoteEntityDoesNotExist(EVENT_NOT_FOUND);
        }
        Task newTaskRequest =
                Task.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .requirement(request.getRequirement())
                        .note(request.getNote())
                        .submission(request.getSubmission())
                        .eventId(request.getEventId())
                        .build();
        Task newTask = taskRepository.save(newTaskRequest);
        return TaskMapper.INSTANCE.taskToTaskResponse(newTask);
    }
}
