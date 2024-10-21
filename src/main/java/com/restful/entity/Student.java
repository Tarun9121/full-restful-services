package com.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Table(name="student")
@Data @AllArgsConstructor @NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * In one-to-one (bidirectional) we don't need to use the JoinColumn because we already giving information about how the tables
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "my_learnings",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> enrolledCourses = new ArrayList<>();
}
