package com.restful.repository;

import com.restful.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean isMobileNoExists(String mobileNo);
//    Student findByIdIsDeletedIsFalse(UUID studentId);
}
