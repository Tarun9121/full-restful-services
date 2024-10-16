package com.restful.service.impl;

import com.restful.entity.Course;
import com.restful.exception.NotFoundException;
import com.restful.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        List<Course> allCourses = courseRepository.findAll();

        allCourses.forEach(course -> {
            if(!ObjectUtils.isEmpty(course.getTeacher())) {
                course.getTeacher().setCourseList(null);
            }
        });

        return allCourses;
    }

    public Course postCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(UUID courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("course not found"));

        if(!ObjectUtils.isEmpty(course.getTeacher())) {
            course.getTeacher().setCourseList(null);
        }

        return course;
    }
}
