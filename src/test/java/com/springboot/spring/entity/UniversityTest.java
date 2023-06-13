package com.springboot.spring.entity;


import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;

        import java.util.Collections;

import java.util.List;

 class UniversityTest {

    @Mock
    private Student mockStudent;

    @Test
     void testGettersAndSetters() {
        Long id = 1L;
        String name = "ABC University";
        University university = new University();

        university.setId(id);
        university.setName(name);
        university.setStudent(Collections.singletonList(mockStudent));

        Assertions.assertEquals(id, university.getId());
        Assertions.assertEquals(name, university.getName());
        Assertions.assertEquals(Collections.singletonList(mockStudent), university.getStudent());
    }


    @Test
     void testConstructorWithNameAndStudents() {
        String name = "ABC University";
        List<Student> students = Collections.singletonList(mockStudent);

        University university = new University(name, students);

        Assertions.assertNull(university.getId());
        Assertions.assertEquals(name, university.getName());
        Assertions.assertEquals(students, university.getStudent());
    }

    @Test
     void testConstructorWithIdNameAndStudents() {
        Long id = 1L;
        String name = "ABC University";
        List<Student> students = Collections.singletonList(mockStudent);

        University university = new University(id, name, students);

        Assertions.assertEquals(id, university.getId());
        Assertions.assertEquals(name, university.getName());
        Assertions.assertEquals(students, university.getStudent());
    }
}
