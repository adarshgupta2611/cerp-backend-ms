package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.AttendanceList;
import com.cerpms.studentservice.projection.AttendanceRecord;
import com.cerpms.studentservice.projection.AttendanceRequestDto;
import com.cerpms.studentservice.repository.AttendanceRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Cacheable(cacheNames = "student_attendance", key = "#studentId")
    public List<AttendanceRecord> showAttendanceByStudent(Long studentId) {
        return attendanceRepository.findAttendanceByStudent(studentId);
    }

    @Override
    public Attendance addAttendance(AttendanceRequestDto attendancedto, String subjectName) {
        Student student = studentRepository.findById(attendancedto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + attendancedto.getStudentId()));

        Subject subject = subjectRepository.findBySubjectName(subjectName);
        Attendance at = attendanceRepository.findAttendanceByStudentAndSubject(student, subject);
        Attendance attendance = new Attendance();
        if (at == null) {
            attendance.setStudent(student);
            attendance.setSubject(subject);
            attendance.setAttendance(attendancedto.getAttendance());
            return attendanceRepository.save(attendance);
        } else {
            return null;
        }
    }

    @Override
    @Cacheable(cacheNames = "subject_attendance", key = "#subjectName")
    public List<AttendanceList> showAttendance(String subjectName) {
        return attendanceRepository.findAllBySubjectNameAndSortedById(subjectName);
    }

    @Override
    @CachePut(cacheNames = "subject_attendance", key = "#subjectName")
    public void updateAttendance(int attendance, String subjectName, Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findBySubjectName(subjectName);
        Attendance at = attendanceRepository.findAttendanceByStudentAndSubject(student, subject);
        at.setAttendance(attendance);
    }
}