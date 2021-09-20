package com.fushioncode.springdatajpa.repositories;

import com.fushioncode.springdatajpa.entity.Course;
import com.fushioncode.springdatajpa.entity.CourseMaterial;
import com.fushioncode.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveTeacher() {

        Optional<Course> course = courseRepository.findCourseByTitle("Introduction to Java");
        Optional<Course> course1 = courseRepository.findCourseByTitle("Advanced Java Programming");
        Course course_1 = new Course();
        Course course_2 = new Course();

        if (course.isPresent()) {
            course_1 = course.get();
        }

        if (course1.isPresent()) {
            course_2 = course1.get();
        }


        Teacher teacherTodd = Teacher.builder()
                .firstName("Todd")
                .lastName("Franklin")
                .Qualification("B.sc")
                .courses(List.of(course_1, course_2))
                .build();

        teacherRepository.save(teacherTodd);
    }

    @Test
    public void saveTeacherCsharpCourse(){
        Optional<Course> course = courseRepository.findCourseByTitle("Intro to c#");
        Course course1 = new Course();

        if(course.isPresent()){
            course1 = course.get();
        }

        Teacher teacher = Teacher.builder()
                .firstName("Hamiir")
                .lastName("Bashir")
                .Qualification("HND")
                .courses(List.of(course1))
                .build();

        teacherRepository.save(teacher);
    }

    @Test
    public void findTeacherById(){
        Optional<Teacher> foundTeacher = teacherRepository.findById(2L);
        Teacher teacher = new Teacher();
        if (foundTeacher.isPresent()){
            teacher = foundTeacher.get();

            System.out.println(teacher);
        }
    }
}