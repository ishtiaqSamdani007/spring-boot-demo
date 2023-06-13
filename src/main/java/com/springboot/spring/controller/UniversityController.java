package com.springboot.spring.controller;

import com.springboot.spring.entity.Student;
import com.springboot.spring.entity.University;
import com.springboot.spring.services.StudentService;
import com.springboot.spring.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;
    @Autowired
    private StudentService studentService;

    // adding the mapping for getting university list "/list"
    @GetMapping("/list")
    public String universityList(Model model){
        // get the list of university from the DB
        List<University> universityList = universityService.findAll();

        model.addAttribute("universities",universityList);

        return "university-list";
    }

    @GetMapping("/showFormForAddUniversity")
    public String showFormForAddUniversity(Model model){
        University university = new University();
        model.addAttribute("university",university);

        return "university-form";
    }

    @PostMapping("/saveUniversity")
    public String saveUniversity(@ModelAttribute("university") University university){
        universityService.save(university);
        return "redirect:/university/list";
    }

    @PostMapping("/showFormForUpdateUniversity")
    public String showFormForUpdateUniversity(@RequestParam("universityId") Long id, Model model){
        University university = universityService.findById(id);
        model.addAttribute("university",university);

        return "university-form";
    }

    @PostMapping("/deleteUniversity")
    public String deleteUniversity(@RequestParam("universityId") Long id){
        universityService.deleteById(id);
        return "redirect:/university/list";
    }

    // Controllers for Students


    @GetMapping("/Students/{universityId}")
    public String getStudents(@PathVariable Long universityId,Model model){
        List<Student> studentList = studentService.findByUniversityId(universityId);
        model.addAttribute("Students",studentList);
        model.addAttribute("universityId",universityId);
        return "student-list";
    }

    @PostMapping("/showFormForAddStudent")
    public String showFormForAddStudent(@RequestParam("universityId") Long universityId, Model model){
        Student student = new Student();
        student.setUniversity(universityService.findById(universityId));
        model.addAttribute("student",student);
        model.addAttribute("universityId",universityId);

        return "student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        int id = Math.toIntExact(student.getUniversity().getId());
//        PRINT ID
        System.out.println(id);
        return "redirect:/university/Students/"+id;
    }

    @PostMapping("/showFormForUpdateStudent")
    public String showFormForUpdateStudent(@RequestParam("studentId") Long id,@RequestParam("universityId") int universityId, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        model.addAttribute("universityId",universityId);
        return "student-form";
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") Long id,@RequestParam("universityId") int universityId){
        studentService.deleteById(id);
        return "redirect:/university/Students/"+universityId;
    }
}
