package com.springboot.spring.controller;


import com.springboot.spring.entity.Student;
import com.springboot.spring.entity.University;
import com.springboot.spring.services.StudentService;
import com.springboot.spring.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniversityRestController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StudentService studentService;


    @GetMapping("/universities")
    public List<University> getUniversities(){
        List<University> universities = universityService.findAll();
        return universities;
    }

    @GetMapping("/Students")
    public List<Student> getStudents(){
        List<Student> Students = studentService.findAll();
        return Students;
    }

    @GetMapping("/Students/{studentId}")
    public Student getStudentById(@PathVariable Long studentId){
        Student student = studentService.findById(studentId);

        if(student == null){
            throw new RuntimeException("The Student not found : "+studentId);
        }
        return student;
    }

    @GetMapping("/universities/{universityId}")
    public University getUniversityById(@PathVariable Long universityId){
        University university = universityService.findById(universityId);

        if(university == null){
            throw new RuntimeException("The University not found : "+universityId);
        }
        return university;
    }

    @GetMapping("/Students/{universityId}")
    public List<Student> getStudentsByUniversityId(@PathVariable Long universityId){
        List<Student> Students = studentService.findByUniversityId(universityId);
        return Students;
    }


    @PostMapping("/universities")
    public University addUniversity(@RequestBody University university){
//        university.setId(0L);
        universityService.save(university);

        return university;
    }

    @PostMapping("/Students")
    public Student addStudent(@RequestBody Student student){
//        student.setId(0L);
        studentService.save(student);

        return student;
    }

    @PutMapping("/universities")
    public University updateUniversity(@RequestBody University university){
        universityService.save(university);

        return university;
    }

    @PutMapping("/Students")
    public Student updateStudent(@RequestBody Student student){
        studentService.save(student);

        return student;
    }

    @DeleteMapping("/universities/{universityId}")
    public String deleteUniversity(@PathVariable Long universityId){
        University university = universityService.findById(universityId);

        if(university == null){
            throw new RuntimeException("The University not found : "+universityId);
        }
        universityService.deleteById(universityId);
        return "Deleted University id : "+universityId;
    }
    @DeleteMapping("/Students/{studentId}")
    public String deleteStudent(@PathVariable Long studentId){
        Student student = studentService.findById(studentId);
        if(student == null){throw new RuntimeException("The Student not found : "+studentId);}
        studentService.deleteById(studentId);
        return "Deleted Student id : "+studentId;}
}
