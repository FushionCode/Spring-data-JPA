package com.fushioncode.springdatajpa.repositories;

import com.fushioncode.springdatajpa.entity.Course;
import com.fushioncode.springdatajpa.entity.CourseMaterial;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseWithCourseMaterial() {
        Course java = Course.builder()
                .title("Introduction to Java")
                .unit(5)
                .build();

        CourseMaterial javaMaterial = CourseMaterial.builder()
                .title("How to do with Java")
                .url("howtodowithjava.com")
                .course(java)
                .build();

        courseMaterialRepository.save(javaMaterial);
    }

    @Test
    public void viewCourseMaterialsWithCourse() {
        List<CourseMaterial> materialsList = courseMaterialRepository.findAll();
        System.out.println("List of courses materials: " + materialsList);
    }

    @Test
    public void saveMoreCourse() {

        Course advancedJavaProgramming = Course.builder()
                .title("Advanced Java Programming")
                .unit(4)
                .build();

        Course introToCSharp = Course.builder()
                .title("Intro to c#")
                .unit(5)
                .build();

        CourseMaterial javaForAdvancedLearners = CourseMaterial.builder()
                .title("Advanced Java programming")
                .url("www.projavaprogramming.com")
                .course(advancedJavaProgramming)
                .build();

        CourseMaterial cSharpBeginners = CourseMaterial.builder()
                .title("C# for beginners")
                .url("www.learntocodeincsharp.com")
                .course(introToCSharp)
                .build();

        courseMaterialRepository.save(javaForAdvancedLearners);
        courseMaterialRepository.save(cSharpBeginners);

    }
}