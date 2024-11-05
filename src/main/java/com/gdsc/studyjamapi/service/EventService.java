package com.gdsc.studyjamapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    public boolean isEventExist(String eventId){
        return "123".equals(eventId);
    }
}
