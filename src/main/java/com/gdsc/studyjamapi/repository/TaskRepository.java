package com.gdsc.studyjamapi.repository;

import com.gdsc.studyjamapi.entity.Task;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {}
