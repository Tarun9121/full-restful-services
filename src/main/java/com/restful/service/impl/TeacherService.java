package com.restful.service.impl;

import com.restful.entity.Course;
import com.restful.entity.Teacher;
import com.restful.exception.NotFoundException;
import com.restful.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher postTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(UUID teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("teacher not found"));
    }
}
