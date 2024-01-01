package com.cerpms.studentservice.projection;

import lombok.Data;

@Data
public class FeedbackList {

    private long studentId;
    private int knowledge;
    private int communication;
    private int punctuality;
    private int teaching;
    private int guidance;
    private String suggestion;
}