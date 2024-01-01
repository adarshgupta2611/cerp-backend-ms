package com.cerpms.studentservice.projection;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private LocalDateTime timestamp;
    private String message;

    public ApiResponse(String message) {
        super();
        this.message = message;
        this.timestamp=LocalDateTime.now();
    }
}