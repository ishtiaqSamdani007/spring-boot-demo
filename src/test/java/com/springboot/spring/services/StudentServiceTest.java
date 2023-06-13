package com.springboot.spring.services;

import com.springboot.spring.dao.StudentRepository;
import com.springboot.spring.entity.Student;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImp studentService;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.studentService).build();
    }

    @Test
    public void testFindByUniversityId(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student( "samdani"));
        students.add(new Student( "Ishtiaq"));
        //when
        when(studentRepository.findByUniversityId(1L)).thenReturn(students);
        List<Student> studentList = studentService.findByUniversityId(1L);

        //then
        assertEquals(students,studentList);
    }

    @Test
    public void testFindAll(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Student 1"));
        students.add(new Student("Student 2"));

        //when
        when(studentRepository.findAll()).thenReturn(students);

        // Call the method to be tested
        List<Student> result = studentService.findAll();

        // then
        assertEquals(2, result.size());
        assertEquals("Student 1", result.get(0).getName());
        assertEquals("Student 2", result.get(1).getName());
    }
    @Test
    public void testFindById(){
        //given
        Student student = new Student("Spring");
        student.setId(1L);
        //when
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student student1 = studentService.findById(1L);
        //then
        assertEquals(student,student1);
    }
    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound(){
        studentService.findById(1L);
    }
    @Test
    public void testSave(){
        //given
        Student student = new Student("student 1");
        //when
        studentService.save(student);
        //then
        verify(studentRepository,times(1)).save(student);
    }
    @Test
    public void testDelete(){
        //given
        Student student = new Student("student 1");
        student.setId(1L);
        // when
        studentService.deleteById(1L);
        //then
        verify(studentRepository,times(1)).deleteById(1L);
    }
}
