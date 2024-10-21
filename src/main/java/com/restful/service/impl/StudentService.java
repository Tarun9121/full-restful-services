package com.restful.service.impl;

import com.restful.entity.Course;
import com.restful.entity.Student;
import com.restful.exception.AccountAlreadyCreatedException;
import com.restful.exception.EntityDeletedException;
import com.restful.exception.FieldIsNullException;
import com.restful.exception.NotFoundException;
import com.restful.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseService courseService;

    public Student postStudent(Student student) {
        if(StringUtils.hasLength(student.getMobileNo())) {
            boolean isAccountCreated = studentRepository.isMobileNoExists(student.getMobileNo());
            if(isAccountCreated) {
                throw new AccountAlreadyCreatedException("account with this mobile no is already exists");
            }
        }
        else {
            throw new FieldIsNullException("mobile no is must field");
        }
        Student savedStudent = studentRepository.save(student);
        savedStudent.getAddress().setStudent(null);
        savedStudent.getEnrolledCourses().forEach(course -> {
            course.setStudents(null);
            course.setTeacher(null);
        });
        return savedStudent;
    }

    public Student getStudentById(UUID studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found with the give id" + studentId));
        if(student.isDeleted()) {
            throw new EntityDeletedException("student account is deleted");
        }
        removeStudentsFromCourse(student);
        return student;
    }

    public Student addCourseToMyLearnings(UUID studentId, UUID courseId) {
        Student student = getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);
        student.getEnrolledCourses().add(course);
        studentRepository.save(student);
        removeStudentsFromCourse(student);
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        allStudents.forEach(this::removeStudentsFromCourse);
        return allStudents;
    }

    private void removeStudentsFromCourse(Student student) {
        if(!ObjectUtils.isEmpty(student.getEnrolledCourses())) {
            student.getEnrolledCourses().forEach(course -> course.setCourseName(null));
        }
    }
}
