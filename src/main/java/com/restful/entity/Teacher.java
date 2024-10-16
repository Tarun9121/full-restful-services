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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity @Table(name="teacher")
@Data @AllArgsConstructor @NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "subject")
    private String subject;

    /**
     * OneToMany: this annotation creates the relationship between the two entities,
     * JoinColumn: creates the foreign key column in the entity based on the relationship, if we don't provide this joincolumn then it will create new table for onetomany annotation, becuase we are not giving the foreign key column by using JoinColumn
     */
    @OneToMany(mappedBy="teacher",cascade = CascadeType.ALL)
    private List<Course> courseList;
}
