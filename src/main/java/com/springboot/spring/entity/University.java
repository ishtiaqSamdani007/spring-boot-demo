package com.springboot.spring.entity;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> student;
    public University() {

    }

    public University(String name, List<Student> student) {
        this.name = name;
        this.student = student;
    }

    public University(Long id, String name, List<Student> student) {
        this.id = id;
        this.name = name;
        this.student = student;}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
