package com.restful.repository;

import com.restful.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    /*
      when you are getting data from an entity which is in one-to-many(bi-direction) relation, then
      for example in this scenario if you are getting the teacher data by id, then the queries generated is
      Hibernate: select teacher_id, teacherName, subject from teacher where teacher_id = ?
      Hibernate: select course_id, teacher_id, course_name, price from course where teacher_id = ?

      If you are retrieving the data from course then the queries will be:
      Hibernate: select course_id, course_name, price, teacher_id from course where course_id = ?
      Hibernate: select teacher_id, teacher_name, subject from teacher where teacher_id = ?
     */

    Teacher findByMobileNo(String mobileNo);

    @Query(nativeQuery = true, value="select * from teacher where is_deleted = false")
    List<Teacher> findAllIsDeletedIsFalse();
}

