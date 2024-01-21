package com.cerpms.studentservice.projection;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiResponse implements Serializable {
    private LocalDateTime timestamp;
    private String message;

    public ApiResponse(String message) {
        super();
        this.message = message;
        this.timestamp=LocalDateTime.now();
    }
}