package com.restful.controller;

import com.restful.entity.Student;
import com.restful.repository.CourseRepository;
import com.restful.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        return studentService.postStudent(student);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") UUID studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public Student addCourseToMyLearnings(@PathVariable("studentId") UUID studentId, @RequestParam("courseId") UUID courseId) {
        return studentService.addCourseToMyLearnings(studentId, courseId);
    }

    @GetMapping
    public List<Student> getMapping() {
        return studentService.getAllStudents();
    }
}