package com.manv.itk_tasks_24._3.__LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;



public class DateTimeContainer {

    @JsonFormat(pattern = "yyyy:MM:dd'##':HH:mm:ss:SS",
            locale = "ru_RU")
    private LocalDateTime dateTime;

    public DateTimeContainer(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTimeContainer() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
