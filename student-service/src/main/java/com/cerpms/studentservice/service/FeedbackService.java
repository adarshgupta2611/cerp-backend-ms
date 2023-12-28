package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.projection.FeedbackDto;
import com.cerpms.studentservice.projection.FeedbackList;

import java.util.List;

public interface FeedbackService {
	Feedback addFeedback(FeedbackDto feedbackdto, Long studentId, String subjectName);
	Feedback getFeedbackById(Long id);
	List<FeedbackList> showFeedback(String subjectName);
}