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
//        teacher.getCourseList()
//                .forEach((course -> course.setTeacher(teacher)));

        Teacher savedTeacher = teacherRepository.save(teacher);
//        removeTeachersFromCourses(savedTeacher);

        return savedTeacher;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAll();

        removeTeachersFromCoursesList(allTeachers);

        return allTeachers;
    }

    public Teacher getTeacherById(UUID teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("teacher not found"));

        removeTeachersFromCourses(teacher);

        return teacher;
    }

    private void removeTeachersFromCourses(Teacher teacher) {
        if(!CollectionUtils.isEmpty(teacher.getCourseList())) {
            teacher.getCourseList().forEach(course -> course.setTeacher(null));
        }
    }

    private void removeTeachersFromCoursesList(List<Teacher> teachersList) {
        teachersList.forEach(this::removeTeachersFromCourses);
    }
}
