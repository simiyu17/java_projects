package com.crud.dao;

import com.crud.model.Student;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Spring boot 2 mockito2 Junit5 Dao example")
class UnitestingStudentDaoTests {

    @Mock
    private StudentDaoImpl stdDao;

    @Test
    void shouldSaveStudentSuccessfully() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Student std = new Student("Test Student", "Nairobi", cal.getTime());

        try {
            when(stdDao.save(std)).thenReturn(std);

            Student savedStudent = stdDao.save(std);
            Assertions.assertNotNull(savedStudent);
            verify(stdDao).save(any(Student.class));

        } catch (Exception e) {
            Assertions.fail("FAIL : " + e.getMessage());
        }

    }

    @Test
    void shouldReturnFindAllStudnets() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        List<Student> students = new ArrayList<>();
        students.add(new Student("Student One", "Kitale", cal.getTime()));
        students.add(new Student("Student Two", "Eldoret", cal.getTime()));
        students.add(new Student("Student Three", "Nakuru", cal.getTime()));
        students.add(new Student("Student Four", "Machakosi", cal.getTime()));

        when(stdDao.getStudents()).thenReturn(students);

        List<Student> expected = stdDao.getStudents();

        Assertions.assertEquals(expected, students);

    }

    @Test
    void shouldFindStudentById() {
        final Long id = 1L;
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(id);

        when(stdDao.findById(id)).thenReturn(student);

        final Student expected = stdDao.findById(id);

        Assertions.assertNotNull(expected);

    }

    @Test
    void shouldDeleteStudentById() {
        final Long id = 1L;
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(id);

        try {
            stdDao.delete(student);
            stdDao.delete(student);

            verify(stdDao, times(2)).delete(student);

        } catch (Exception e) {
            Assertions.fail("FAIL : " + e.getMessage());
        }

    }

}
