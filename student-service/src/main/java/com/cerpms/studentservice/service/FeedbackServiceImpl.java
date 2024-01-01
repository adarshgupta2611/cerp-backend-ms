package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.FeedbackList;
import com.cerpms.studentservice.repository.FeedbackRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Feedback addFeedback(Feedback feedback, Long studentId, String subjectName) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("student not found with id: " + studentId));
        Subject subject = subjectRepository.findBySubjectName(subjectName).orElseThrow(() -> new EntityNotFoundException("subject not found with name " + subjectName));
        log.info("Student is " + student.toString());
        log.info("Subject is " + subject.toString());
        Optional<Feedback> fd = feedbackRepository.findByStudentAndSubject(student, subject);
        if (fd.isPresent()) {
            throw new EntityExistsException("Feedback already exists");
        } else {
            feedback.setStudent(student);
            feedback.setSubject(subject);
            return feedbackRepository.save(feedback);
        }

    }

    @Override
    public List<FeedbackList> showFeedback(String subjectName) {
        return feedbackRepository.findAllBySubjectNameAndSortedById(subjectName);
    }
}