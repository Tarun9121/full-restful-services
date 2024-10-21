package com.restful.service.impl;

import com.restful.entity.Course;
import com.restful.entity.Teacher;
import com.restful.exception.AccountAlreadyCreatedException;
import com.restful.exception.EntityDeletedException;
import com.restful.exception.FieldIsNullException;
import com.restful.exception.NotFoundException;
import com.restful.repository.CourseRepository;
import com.restful.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Teacher postTeacher(Teacher teacher) {
        teacher.getCourseList().forEach((course -> course.setTeacher(teacher)));
        if(StringUtils.hasLength(teacher.getMobileNo())) {
            Teacher teacherData = teacherRepository.findByMobileNo(teacher.getMobileNo());
            if(!ObjectUtils.isEmpty(teacherData)) {
                throw new AccountAlreadyCreatedException("teacher account with this mobile no is already exists");
            }
            else {
                Teacher savedTeacher = teacherRepository.save(teacher);
                removeTeachersFromCourses(savedTeacher);
                return savedTeacher;
            }
        }
        else {
            throw new FieldIsNullException("mobile no is a must field");
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = teacherRepository.findAllIsDeletedIsFalse();

        removeTeachersFromCoursesList(allTeachers);

        return allTeachers;
    }

    public Teacher getTeacherById(UUID teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("teacher not found"));

        if(teacher.isDeleted()) {
            throw new EntityDeletedException("teacher account is deleted");
        }
        removeTeachersFromCourses(teacher);

        return teacher;
    }

    public Teacher createCourses(UUID teacherId, List<Course> courseList) {
        Teacher teacher = getTeacherById(teacherId);

        if(CollectionUtils.isEmpty(courseList)) {
            throw new FieldIsNullException("please add the courses");
        }
        teacher.getCourseList().addAll(courseList);
        courseList.forEach(course -> course.setTeacher(teacher));
        courseRepository.saveAll(courseList);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        removeTeachersFromCourses(updatedTeacher);
        return updatedTeacher;
    }

    public void disableAccount(UUID teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.setDeleted(true);
        teacherRepository.save(teacher);
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
