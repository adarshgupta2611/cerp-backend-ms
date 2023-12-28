package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.FeedbackDto;
import com.cerpms.studentservice.projection.FeedbackList;
import com.cerpms.studentservice.repository.FeedbackRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Feedback addFeedback(FeedbackDto feedbackdto, Long studentId, String subjectName) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("student not found with id: " + studentId));
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        if (subject == null) {
            throw new EntityNotFoundException("subject not found with name " + subjectName);
        }
        Feedback fd = feedbackRepository.findByStudentAndSubject(student, subject);
        Feedback feedback = new Feedback();

        if (fd == null) {
            feedback.setKnowledge(feedbackdto.getKnowledge());
            feedback.setCommunication(feedbackdto.getCommunication());
            feedback.setGuidance(feedbackdto.getGuidance());
            feedback.setPunctuality(feedbackdto.getPunctuality());
            feedback.setTeaching(feedbackdto.getTeaching());
            feedback.setSuggestion(feedbackdto.getSuggestion());

            feedback.setStudent(student);
            feedback.setSubject(subject);
            return feedbackRepository.save(feedback);
        } else {
            return null;
        }

    }

    @Override
    @Cacheable(cacheNames = "student_feedback", key = "#id")
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "subject_feedback", key = "#id")
    public List<FeedbackList> showFeedback(String subjectName) {
        return feedbackRepository.findAllBySubjectNameAndSortedById(subjectName);
    }
}