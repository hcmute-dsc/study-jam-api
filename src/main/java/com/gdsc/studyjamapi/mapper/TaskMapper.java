package com.gdsc.studyjamapi.mapper;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import com.gdsc.studyjamapi.dto.response.TaskResponse; // Import your TaskResponse DTO
import com.gdsc.studyjamapi.entity.Task; // Import your Task entity
import java.util.Arrays;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
  TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

  TaskResponse taskToTaskResponse(Task task);

  Task taskRequestToTask(CreateTaskRequest taskRequest);

  List<TaskResponse> listTaskToListTaskResponse(List<Task> tasks);

  List<Task> listTaskRequestToListTask(List<CreateTaskRequest> tasks);

  // Method to convert List<String> to a period-separated String
  default String mapListToString(List<String> value) {
    return value != null ? String.join(".", value) : null; // Joining with period
  }

  // Method to convert a period-separated String to List<String>
  default List<String> mapStringToList(String str) {
    return str != null ? Arrays.asList(str.split("\\.")) : null; // Split by period
  }
}
