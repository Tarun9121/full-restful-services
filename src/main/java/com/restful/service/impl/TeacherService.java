package com.restful.service.impl;

import com.restful.entity.Teacher;
import com.restful.exception.NotFoundException;
import com.restful.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher postTeacher(Teacher teacher) {
        teacher.getCourseList()
                .forEach((course -> course.setTeacher(teacher)));

        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();

        allTeachers.forEach(teacher -> {
                    if (!CollectionUtils.isEmpty(teacher.getCourseList())) {
                        teacher.getCourseList().forEach(course -> course.setTeacher(null));
                    }
                });

        return allTeachers;
    }

    public Teacher getTeacherById(UUID teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("teacher not found"));

        if(!CollectionUtils.isEmpty(teacher.getCourseList())) {
            teacher.getCourseList().forEach(course -> course.setTeacher(null));
        }

        return teacher;
    }
}
