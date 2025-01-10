package com.gdsc.studyjamapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    //Todo: This method needs to be implemented later
    public boolean doesEventExist(String eventId) {
        return "123".equals(eventId);
    }
}
