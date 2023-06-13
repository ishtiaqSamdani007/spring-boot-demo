package com.springboot.spring.controller;




import com.springboot.spring.entity.Student;
import com.springboot.spring.entity.University;
import com.springboot.spring.services.StudentService;
import com.springboot.spring.services.UniversityService;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityControllerRestTest {


    @Mock
    private UniversityService universityService;
    @Mock
    private StudentService studentService;

    @Mock
    private Student mockStudent;

    List<Student> students = Collections.singletonList(mockStudent);

    @InjectMocks
    private UniversityRestController universityRestController;


    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(universityRestController).build();
    }


    @Test
    public void testGetUniversity() {


        List<University> universities = new ArrayList<>();


        universities.add(new University("Test University", students));
        universities.add(new University("Test University 2",students));


        when(universityService.findAll()).thenReturn(universities);


        List<University> universityList = universityRestController.getUniversities();
        assertEquals(universities, universityList);
    }


    @Test
    public void testGetUniversityById() {
        University university = new University("Test University",students);


        when(universityService.findById(1L)).thenReturn(university);


        University university1 = universityRestController.getUniversityById(1L);
        assertEquals(university, university1);
    }


    @Test(expected = RuntimeException.class)
    public void testGetUniversityByIdNotFound() {
        University university = universityRestController.getUniversityById(1L);
    }


    @Test
    public void testAddUniversity() {
        University university = new University("Test University",students);
        University university1 = universityRestController.addUniversity(university);
        assertEquals(university, university1);
        verify(universityService, times(1)).save(university);
    }


    @Test
    public void testUpdateUniversity() {
        University university = new University("Test University",students);
        University university1 = universityRestController.updateUniversity(university);
        assertEquals(university, university1);
        verify(universityService, times(1)).save(university);
    }


    @Test(expected = RuntimeException.class)
    public void testDeleteUniversityFailure() throws Exception {
        University university = new University("Test University",students);
        universityRestController.addUniversity(university);
        universityRestController.deleteUniversity(1L);
        mockMvc.perform(delete("/university/1")).andExpect(status().isNotFound());
        verify(universityService, times(1)).deleteById(1L);
    }


    @Test
    public void testDeleteUniversitySuccess() {


        Mockito.when(universityService.findById(1L)).thenReturn(new University("Test University",students));
        University university = new University("Test University",students);
        universityRestController.addUniversity(university);
        universityRestController.deleteUniversity(1L);
        verify(universityService, times(1)).deleteById(1L);
    }


    @Test(expected = RuntimeException.class)
    public void testDeleteUniversityNotFound() {
        universityRestController.deleteUniversity(1L);
    }


    @Test
    public void testGetStudents() {
        List<Student> students = new ArrayList<>();


        students.add(new Student("Test"));
        students.add(new Student("Test 2"));


        when(studentService.findAll()).thenReturn(students);


        List<Student> studentList = universityRestController.getStudents();


        assertEquals(students, studentList);






    }


    @Test
    public void testGetStudentById() {
        Student student = new Student("Test");


        when(studentService.findById(1L)).thenReturn(student);


        Student student1 = universityRestController.getStudentById(1L);
        assertEquals(student, student1);
    }


    @Test(expected = RuntimeException.class)
    public void testGetStudentByIdNotFound() {
        Student student = universityRestController.getStudentById(1L);
    }


    @Test
    public void getStudentsByUniversityId() {
        List<Student> students = new ArrayList<>();


        students.add(new Student("Test"));
        students.add(new Student("Test 2"));


        when(studentService.findByUniversityId(1L)).thenReturn(students);


        List<Student> studentList = universityRestController.getStudentsByUniversityId(1L);


        assertEquals(students, studentList);
    }


    @Test
    public void testAddStudent() {
        Student student = new Student("Test");
        Student student1 = universityRestController.addStudent(student);
        assertEquals(student, student1);
        verify(studentService, times(1)).save(student);
    }


    @Test
    public void testUpdateStudent() {
        Student student = new Student("Test");
        Student student1 = universityRestController.updateStudent(student);
        assertEquals(student, student1);
        verify(studentService, times(1)).save(student);
    }


    @Test(expected = RuntimeException.class)
    public void testDeleteStudentFailure() {
        Student student = new Student("Test");
        universityRestController.deleteStudent(student.getId());
        verify(studentService, times(1)).deleteById(student.getId());
    }


    @Test
    public void testDeleteStudentSuccess() {
        Long studentId = 1L;
        Student mockStudent = new Student(studentId, "John Doe", null);

        // Mock the behavior of studentService.findById()
        Mockito.when(studentService.findById(studentId)).thenReturn(mockStudent);


        // Call the method under test
        universityRestController.addStudent(mockStudent);
        String result = universityRestController.deleteStudent(studentId);


        // Verify that the correct methods were called
        Mockito.verify(studentService, Mockito.times(1)).findById(studentId);
        Mockito.verify(studentService, Mockito.times(1)).deleteById(studentId);

        // Assert the return value and message
        Assertions.assertEquals("Deleted Student id : " + studentId, result);

    }


    @Test(expected = RuntimeException.class)
    public void testDeleteStudentNotFound() {
        universityRestController.deleteStudent(1L);
    }
}

