package com.gdsc.studyjamapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gdsc.studyjamapi.dto.response.TaskResponse;
import com.gdsc.studyjamapi.entity.Task;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskResponse taskToTaskResponse(Task task);

    List<TaskResponse> listTaskToListTaskResponse(List<Task> tasks);
}
