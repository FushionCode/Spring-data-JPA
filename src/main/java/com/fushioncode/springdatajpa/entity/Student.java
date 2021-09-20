package com.fushioncode.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(
        name = "emailAddress_unique",
        columnNames = "email_address"
))
public class Student {

    @Id
    @SequenceGenerator(
            sequenceName = "studentSequence",
            name = "studentSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studentSequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(name = "email_address",
            nullable = false)
    private String emailAddress;

    @Embedded
    private Guardian guardian;

    @ManyToMany
    @JoinTable(
            name = "student_course_mapping",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            )
    )
    private List<Course> courses;


}
