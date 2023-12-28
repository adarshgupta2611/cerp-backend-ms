package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.projection.FeedbackDto;
import com.cerpms.studentservice.projection.FeedbackList;
import com.cerpms.studentservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:3000/")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/{studentId}/{subjectName}")
    public ResponseEntity<?> addFeedback(@PathVariable Long studentId, @PathVariable String subjectName, @RequestBody FeedbackDto feedbackdto) {
        Feedback feedback = feedbackService.addFeedback(feedbackdto, studentId, subjectName);
        if (feedback != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
        } else {
            return ResponseEntity.status(500).body("Feedback is already given");

        }
    }

    @GetMapping("/{subjectName}")
    public ResponseEntity<?> showFeedback(@PathVariable String subjectName) {
        List<FeedbackList> feedbackList = feedbackService.showFeedback(subjectName);
        if (feedbackList != null)
            return ResponseEntity.ok(feedbackList);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid subject name");
    }
}