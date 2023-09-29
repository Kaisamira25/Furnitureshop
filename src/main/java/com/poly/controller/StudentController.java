package com.poly.controller;

import com.poly.model.Student;
import com.poly.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/public")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public String showStudentList(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "list";
    }

    @GetMapping("/students-add")
    public String showAddStudentForm() {
        return "add-student";
    }

    @PostMapping("/students-add")
    public String addStudent(@RequestParam String name, @RequestParam String phone) {
        // Tạo một sinh viên mới và lưu vào cơ sở dữ liệu
        Student student = Student.builder().name(name).phone(phone).build();
        studentRepository.save(student);
        return "redirect:/public/students";
    }
}
