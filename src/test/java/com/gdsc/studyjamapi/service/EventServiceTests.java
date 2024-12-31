package com.gdsc.studyjamapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EventServiceTests {
    @Autowired
    private EventService eventService;

    @Test
    void doesEventExist_ValidId_ReturnsTrue() {
        String request_event_id = "123";

        boolean result = eventService.doesEventExist(request_event_id);

        assertTrue(result, "The event with ID '123' should exist.");
    }

    @Test
    void doesEventExist_InValidId_ReturnsTrue() {
        String request_event_id = "456";

        boolean result = eventService.doesEventExist(request_event_id);

        assertFalse(result, "The event with ID '456' should exist.");
    }
}
