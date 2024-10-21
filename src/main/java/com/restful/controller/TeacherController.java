package com.restful.controller;

import com.restful.entity.Course;
import com.restful.entity.Teacher;
import com.restful.service.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public Teacher postTeacher(@RequestBody Teacher teacher) {
        return teacherService.postTeacher(teacher);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping("/{teacherId}")
    public Teacher createCourses(@PathVariable("teacherId") UUID teacherId, @RequestBody List<Course> coursesList) {
        return teacherService.createCourses(teacherId, coursesList);
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") UUID teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("/{teacherId}")
    public void disableAccount(@PathVariable("teacherId") UUID teacherId) {
        teacherService.disableAccount(teacherId);
    }
}
