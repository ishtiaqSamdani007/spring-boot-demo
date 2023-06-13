package com.springboot.spring.services;

import com.springboot.spring.DOA.UniversityRepository;
import com.springboot.spring.entity.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImp implements UniversityService {


    @Autowired(required = true)
    private UniversityRepository universityRepository;
    @Override
    @Transactional
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    @Transactional
    public University findById(Long theId) {
        Optional<University> result = universityRepository.findById(theId);

        University university = null;

        if (result.isPresent()) {
            university = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find University id - " + theId);
        }

        return university;

    }

    @Override
    @Transactional
    public void save(University university) {

        universityRepository.save(university);

    }

    @Override
    @Transactional
    public void deleteById(Long theId) {

        universityRepository.deleteById(theId);

    }
}
