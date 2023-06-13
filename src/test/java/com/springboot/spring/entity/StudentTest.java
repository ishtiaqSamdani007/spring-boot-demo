package com.springboot.spring.entity;

import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import static org.mockito.Mockito.*;

 class StudentTest {

    @Mock
    private University mockUniversity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
     void testGettersAndSetters() {
        Student student = new Student();

        Long id = 1L;
        String name = "John Doe";

        student.setId(id);
        student.setName(name);
        student.setUniversity(mockUniversity);

        Assertions.assertEquals(id, student.getId());
        Assertions.assertEquals(name, student.getName());
        Assertions.assertEquals(mockUniversity, student.getUniversity());
    }

    @Test
     void testToString() {
        Long id = 1L;
        String name = "John Doe";
        Student student = new Student(id, name, mockUniversity);
        Student student2 = new Student(name, mockUniversity);
        Student student3 = new Student(name);
        String expectedToString = "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university=" + mockUniversity +
                '}';

        Assertions.assertEquals(expectedToString, student.toString());
        Assertions.assertEquals(name, student2.getName());
        Assertions.assertEquals(name, student3.getName());
    }


}
