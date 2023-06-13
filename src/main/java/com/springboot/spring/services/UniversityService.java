package com.springboot.spring.services;

import com.springboot.spring.entity.University;

import java.util.List;


public interface UniversityService {

    public List<University> findAll();

    public University findById(Long theId);

    public void save(University university);

    public void deleteById(Long theId);
}
