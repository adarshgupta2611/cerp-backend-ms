package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.AttendanceRecord;
import com.cerpms.studentservice.repository.AttendanceRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Cacheable(cacheNames = "student_attendance_getAttendanceService", key = "#studentId")
    public List<AttendanceRecord> getAttendanceService(Long studentId) {
        return attendanceRepository.findAttendanceByStudentId(studentId);
    }

    @Override
    public Attendance addAttendanceService(String subjectName, Long studentId, int attendance) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Subject subject = subjectRepository.findBySubjectName(subjectName).orElseThrow();

        Optional<Attendance> newAttendanceOptional = attendanceRepository.findBySubjectAndStudent(subject, student);
        if (newAttendanceOptional.isPresent()) {
            return null;
        } else {
            Attendance newAttendance = new Attendance(attendance, subject, student);
            attendanceRepository.save(newAttendance);
            return newAttendance;
        }
    }

    @Override
    public Attendance updateAttendanceService(String subjectName, Long studentId, int attendance) {
        Student student = studentRepository.findById(studentId).get();
        Subject subject = subjectRepository.findBySubjectName(subjectName).get();
        Attendance at = attendanceRepository.findAttendanceByStudentAndSubject(student, subject);
        at.setAttendance(attendance);
        return at;
    }
}