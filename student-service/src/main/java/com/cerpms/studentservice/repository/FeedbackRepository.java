package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Feedback;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.FeedbackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByStudentAndSubject(Student student, Subject subject);

    @Query("Select new com.cerpms.studentservice.projection.FeedbackList(f.student.id as studentId, f.knowledge as knowledge, f.communication as communication, f.punctuality as punctuality, f.teaching as teaching, f.guidance as guidance, f.suggestion as suggestion) from Feedback f join f.subject sb join f.student st where sb.subjectName =:subjectName order by st.id")
    List<FeedbackList> findAllBySubjectNameAndSortedById(@Param("subjectName") String subjectName);
}