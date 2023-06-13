package com.springboot.spring.controller;


import com.springboot.spring.entity.Student;
import com.springboot.spring.entity.University;
import com.springboot.spring.services.UniversityService;
import com.springboot.spring.services.StudentService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityControllerTest {
    @Mock
    private UniversityService universityService;
    @Mock
    private StudentService studentService;

    @Mock
    private Student mockStudent;
    @Mock
    private Model model;
    @InjectMocks
    private UniversityController universityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(universityController).build();
    }

    @Test
    public void testUniversityList(){
        // given
        List<University> universityList = new ArrayList<>();
        List<Student> students = Collections.singletonList(mockStudent);
        universityList.add(new University("Test University",students));
        universityList.add(new University("Test University 2",students));
        when(universityService.findAll()).thenReturn(universityList);
        //when
        String view = universityController.universityList(model);
        assertEquals("university-list",view);
        verify(universityService,times(1)).findAll();

    }
    @Test
    public void testShowFormForAdd(){
        String view = universityController.showFormForAddUniversity(model);
        assertEquals("university-form",view);
        verify(model,times(1)).addAttribute(eq("university"),any(University.class));
    }
    @Test
    public void testShowFormForUpdate(){
        //given
        University university = new University();
        university.setId(1L);
        when(universityService.findById(1L)).thenReturn(university);
        //when
        String view = universityController.showFormForUpdateUniversity(1L,model);
        //then
        assertEquals("university-form",view);
        verify(universityService,times(1)).findById(1L);
        verify(model,times(1)).addAttribute(eq("university"),any(University.class));
    }
    @Test
    public void testSaveUniversity(){
        University university = new University();
        String view = universityController.saveUniversity(university);
        assertEquals("redirect:/university/list",view);
        verify(universityService,times(1)).save(university);
    }
    @Test
    public void testDelete(){
        University university = new University();
        university.setId(1L);
        String view = universityController.deleteUniversity(1L);
        assertEquals("redirect:/university/list",view);
        verify(universityService,times(1)).deleteById(1L);
    }
    @Test
    public void testGetStudents(){
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Spring"));
        students.add(new Student("Java"));

        when(studentService.findByUniversityId(1L)).thenReturn(students);
        //when
        String view= universityController.getStudents(1L,model);
        //then
        assertEquals("student-list",view);
        verify(studentService,times(1)).findByUniversityId(1L);
    }
//    @Test
//    public void testShowFormForAddStudent(){
//        String view = universityController.showFormForAddStudent(1L,model);
//        assertEquals("student-form",view);
//        verify(model,times(1)).addAttribute("universityId",1);
//    }
    @Test
    public void testShowFormForUpdateStudent(){
        String view = universityController.showFormForUpdateStudent(1L,1,model);
        assertEquals("student-form",view);
        verify(studentService,times(1)).findById(1L);
        verify(model,times(1)).addAttribute("universityId",1);
    }
    @Test
    public void testSaveStudent(){
        Student student = new Student();
        student.setId(1L);
        student.setName("samdani");

        List<Student> students = Collections.singletonList(mockStudent);

        student.setUniversity(new University("Test University",students));
        student.getUniversity().setId(1L);


        String view = universityController.saveStudent(student);
        assertEquals("redirect:/university/Students/1",view);
        verify(studentService,times(1)).save(student);
    }
    @Test
    public void testDeleteStudent(){
        String view = universityController.deleteStudent(1L,1);
        assertEquals("redirect:/university/Students/1",view);
        verify(studentService,times(1)).deleteById(1L);
    }
}
