package com.cerpms.adminservice.projection;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ScheduleRequestDto implements Serializable {
    private Long Id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String Location;
    private String subjectName;

}