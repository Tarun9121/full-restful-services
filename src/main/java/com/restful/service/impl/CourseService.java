package com.restful.service.impl;

import com.restful.entity.Course;
import com.restful.exception.NotFoundException;
import com.restful.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course postCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(UUID courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("course not found"));
    }
}
