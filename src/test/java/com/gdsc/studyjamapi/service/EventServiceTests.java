package com.gdsc.studyjamapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EventServiceTests {
  @Autowired private EventService eventService;

  @Test
  void doesEventExist_validId_returnsTrue() {
    String requestEventId = "123";

    boolean result = eventService.doesEventExist(requestEventId);

    assertTrue(result, "The event with ID " + requestEventId + " exists.");
  }

  @Test
  void doesEventExist_inValidId_returnsFalse() {
    String requestEventId = "456";

    boolean result = eventService.doesEventExist(requestEventId);

    assertFalse(result, "The event with ID " + requestEventId + " doesn't exist.");
  }
}
