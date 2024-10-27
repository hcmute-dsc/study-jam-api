package com.gdsc.studyjamapi.repository;

import com.gdsc.studyjamapi.entity.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {}
