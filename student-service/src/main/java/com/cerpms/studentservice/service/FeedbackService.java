package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.projection.FeedbackList;

import java.util.List;

public interface FeedbackService {
    Feedback addFeedback(Feedback feedback, Long studentId, String subjectName);

    List<FeedbackList> showFeedback(String subjectName);
}