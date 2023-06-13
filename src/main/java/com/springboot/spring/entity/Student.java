package com.springboot.spring.entity;
import jakarta.persistence.*;



@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "university_id")
    private University university;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }


    public Student(String name, University university) {
        this.name = name;
        this.university = university;
    }
    public Student(Long id, String name, University university) {
        this.id = id;
        this.name = name;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public University getUniversity() {
        return university;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university=" + university +
                '}';
    }
}

