package com.manv.itk_tasks_24._3.__LocalDateTime.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manv.itk_tasks_24._3.__LocalDateTime.DateTimeContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DateTimeService {
    private final ObjectMapper objectMapper;

    @Autowired
    public DateTimeService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public DateTimeContainer convertLocalDateTimeToString(LocalDateTime inputLocalDateTime) {
        return objectMapper.convertValue(new DateTimeContainer(inputLocalDateTime), DateTimeContainer.class);
    }

}
