package com.springboot.spring.services;

import com.springboot.spring.dao.UniversityRepository;

import com.springboot.spring.entity.Student;
import com.springboot.spring.entity.University;

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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversityServiceTest {
    @Mock
    private UniversityRepository universityRepository;

    @Mock
    private Student mockStudent;
    @InjectMocks
    private UniversityServiceImp universityService;

    private MockMvc mockMvc;



    @BeforeEach
    public void setUp() throws Exception{
        // MockitoAnnotations. openMocks(this) call tells Mockito to scan this test class instance for any fields annotated with the @Mock annotation and initialize those fields as mocks
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.universityService).build();
    }

    @Test
    public void testFindAll(){
        //given
        List<Student> students = Collections.singletonList(mockStudent);
        List<University> universityList = new ArrayList<>();
        universityList.add(new University( "Test University",students));
        universityList.add(new University("Test University 2",students));
        //when
        when(universityRepository.findAll()).thenReturn(universityList);
        List<University> university = universityService.findAll();

        // then
        assertEquals(universityList,university);
        assertEquals("Test University",university.get(0).getName());
        assertEquals(2,university.size());

    }

    @Test
    public void testFindById(){
        List<Student> students = Collections.singletonList(mockStudent);
        University university = new University( "Test University",students);
        //when
        when(universityRepository.findById(0L)).thenReturn(Optional.of(university));
        University university1=universityService.findById(0L);
        //then
        assertEquals(university,university1);
    }
    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound(){
        universityService.findById(1L);
    }

    @Test
    public void testSave(){
        //given
        List<Student> students = Collections.singletonList(mockStudent);
        University university = new University( "Test",students);
        //when
        universityService.save(university);
        //then
        verify(universityRepository,times(1)).save(university);
    }
    @Test
    public void testDelete(){
        //when
        universityService.deleteById(1L);
        //then
        verify(universityRepository,times(1)).deleteById(1L);
    }
}
