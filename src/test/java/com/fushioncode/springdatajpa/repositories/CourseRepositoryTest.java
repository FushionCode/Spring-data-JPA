package com.fushioncode.springdatajpa.repositories;

import com.fushioncode.springdatajpa.entity.Course;
import com.fushioncode.springdatajpa.entity.CourseMaterial;
import com.fushioncode.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void courseList() {
        List<Course> courseList = courseRepository.findAll();
        System.out.println("Course Lists: " + courseList);
    }

    //Testing ManyToOne relationship between Course and Teacher
    @Test
    public void saveNewCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Shedrach")
                .lastName("Adamu")
                .Qualification("Msc")
                .build();

        Course course = Course.builder()
                .title("Frontend from beginners to advance")
                .unit(5)
                //.teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    /*
    Using Paginatin and sorting
    @Test
    public void findAll(){
        Pageable findPageWithTwoCourseObject = PageRequest.of(0, 2);

        List<Course> courses =
                courseRepository.findAll(findPageWithTwoCourseObject).get;
    }*/


}