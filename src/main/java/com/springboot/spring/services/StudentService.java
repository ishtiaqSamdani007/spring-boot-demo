package com.springboot.spring.services;

import com.springboot.spring.entity.Student;

import java.util.List;

public interface StudentService {

    public  List<Student> findByUniversityId(Long theId);
    public List<Student> findAll();

    public Student findById(Long theId);

    public void save(Student student);

    public void deleteById(Long theId);
}
