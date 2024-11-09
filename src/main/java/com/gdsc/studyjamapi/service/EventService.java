package com.gdsc.studyjamapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    public boolean doesEventExist(String eventId){
        return "123".equals(eventId);
    }
}
