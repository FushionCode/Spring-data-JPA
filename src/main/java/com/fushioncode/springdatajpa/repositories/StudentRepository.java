package com.fushioncode.springdatajpa.repositories;

import com.fushioncode.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByLastName(String lastName);

    List<Student> findStudentByFirstNameContaining(String name);

    //using JPQL
    @Query("select s from Student s where s.guardian.name = ?1")
    List<Student> getStudentsByGuardian_Name(String name);

    //using JPQL native
    @Query(
            value = "select * from student s where s.email_address = ?1",
            nativeQuery = true
    )
    Optional<Student> locateStudentByEmailAddress(String email);


    @Modifying
    @Transactional
    @Query(
            value = "update student s set s.firstName=?1 where s.email_address=?2",
            nativeQuery = true
    )
    void updateStudentFirstNameByEmail(String firstName, String email);
}


