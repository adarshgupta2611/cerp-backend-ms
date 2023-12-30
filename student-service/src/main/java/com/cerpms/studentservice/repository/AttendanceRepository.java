package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.AttendanceRecord;
import com.cerpms.studentservice.projection.AttendanceStudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT new com.cerpms.studentservice.projection.AttendanceRecord(a.attendance as attendance, s.subjectName as subjectName, st.firstName as firstName, st.lastName as lastName, st.email as email, st.gender as gender, st.address as address, st.course as course) FROM Attendance a JOIN a.subject s JOIN a.student st  where a.student.id = :studentId")
    List<AttendanceRecord> findAttendanceByStudentId(@Param("studentId") Long studentId);

    Optional<Attendance> findBySubjectAndStudent(Subject subject, Student student);

    Attendance findAttendanceByStudentAndSubject(Student student, Subject subject);

    @Query("Select new com.cerpms.studentservice.projection.AttendanceStudentSubject(a.student as student, a.attendance as attendance) from Attendance a join a.student s join a.subject sb where a.subject.subjectName=:subjectName ORDER BY s.id")
    List<AttendanceStudentSubject> findAllBySubjectNameAndSortedById(@Param("subjectName") String subjectName);
}