package com.fushioncode.springdatajpa.repositories;

import com.fushioncode.springdatajpa.entity.Course;
import com.fushioncode.springdatajpa.entity.Guardian;
import com.fushioncode.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class StudentRepositoriesTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveNewStudent() {
        Guardian guardian = Guardian.builder()
                .email("stephany@example.com")
                .name("Stephanie James")
                .mobile("12345678901")
                .build();

        Student student = Student.builder()
                .firstName("Jason")
                .lastName("James")
                .emailAddress("student_jason@example.com")
                .guardian(guardian)
                .build();

        Student student1 = Student.builder()
                .firstName("julie")
                .lastName("James")
                .emailAddress("stuent_julie@example.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
        studentRepository.save(student1);
    }

    @Test
    public void viewStudentList() {
        List<Student> getAllStudent = studentRepository.findAll();

        System.out.println("Registered Student: " + getAllStudent);
    }

    @Test
    public void findStudentByLastName() {
        List<Student> studentByLastName = studentRepository.findStudentByLastName("James");
        System.out.println("Students with lastName James are: " + studentByLastName);
    }

    @Test
    public void findWithFirstNameContaining() {
        List<Student> findStudentByFirstNameChar = studentRepository.findStudentByFirstNameContaining("Jas");
        System.out.println("Student with firstName containing character Jas: " + findStudentByFirstNameChar);
    }

    @Test
    public void getStudentsWithGuardianName() {
        List<Student> getStudentByGuardianName = studentRepository.getStudentsByGuardian_Name("Stephanie James");
        System.out.println("Stephanie James is the registered guardian of: " + getStudentByGuardianName);
    }

    @Test
    public void findStudentByEmail() {
        Optional<Student> findStudentByEmail =
                studentRepository.locateStudentByEmailAddress("student_jason@example.com");
    }

    @Test
    public void updateStudentInformationByEmail() {
        studentRepository.updateStudentFirstNameByEmail("Jason Junior", "student_jason@example.com");
    }

    //Testing ManyToMany mapping
    @Test
    public void addStudentWithCourse() {
        Optional<Course> courses = courseRepository.findCourseByTitle("Intro to c#");
        Optional<Course> courses1 = courseRepository.findCourseByTitle("Frontend from beginners to advance");

        Course course_1 = new Course();
        Course course_2 = new Course();

        if (courses.isPresent()) course_1 = courses.get();
        if (courses1.isPresent()) course_2 = courses1.get();

        Guardian guardian = Guardian.builder()
                .name("Oscar Peterson")
                .email("oscar.peterson@gmail.com")
                .mobile("09876543212")
                .build();

        Student student = Student.builder()
                .firstName("Andrew")
                .lastName("Peterson")
                .emailAddress("student_andrew@example.com")
                .guardian(guardian)
                .courses(List.of(course_1, course_2))
                .build();

        studentRepository.save(student);
    }
}
