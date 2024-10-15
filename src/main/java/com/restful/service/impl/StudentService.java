package com.restful.service.impl;

import com.restful.entity.Student;
import com.restful.exception.NotFoundException;
import com.restful.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student postStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("student not found with this id: " + studentId));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
