package com.cerpms.studentservice.projection;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackList  implements Serializable {

    private long studentId;
    private int knowledge;
    private int communication;
    private int punctuality;
    private int teaching;
    private int guidance;
    private String suggestion;
}