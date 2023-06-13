package com.springboot.spring.services;

import com.springboot.spring.dao.StudentRepository;
import com.springboot.spring.dao.UniversityRepository;
import com.springboot.spring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private UniversityRepository universityRepository;

    @Override
    @Transactional
    public List<Student> findByUniversityId(Long theId) {

        return studentRepository.findByUniversityId(theId);
    }

    @Override
    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student findById(Long theId) {
        Optional<Student> result = studentRepository.findById(theId);

        Student student = null;

        if(result.isPresent()){
            student=result.get();
        }
        else {
            throw new RuntimeException(" Did not find the Student with id - "+theId);
        }

        return student;
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteById(Long theId) {
        studentRepository.deleteById(theId);

    }
}
