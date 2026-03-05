package com.example.schoolmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // =========================================
    // YÊU CẦU 1 (1 điểm) - THÊM SINH VIÊN
    // POST: /api/students
    // =========================================
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Tên sinh viên không được để trống");
        }

        return ResponseEntity.ok(service.addStudent(student));
    }

    // =========================================
    // YÊU CẦU 2 (1 điểm) - XÓA SINH VIÊN
    // POST: /api/students/delete/{id}
    // =========================================
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        service.deleteStudent(id);
        return ResponseEntity.ok("Đã xóa sinh viên ID = " + id);
    }

    // =========================================
    // YÊU CẦU 3 (1 điểm) - TÌM KIẾM
    // GET: /api/students/search?name=abc
    // =========================================
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam String name) {
        return service.findByName(name);
    }

    // =========================================
    // YÊU CẦU 4 (1 điểm) - LẤY THEO ID
    // GET: /api/students/{id}
    // =========================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // =========================================
    // YÊU CẦU 5 (1 điểm) - GET ALL
    // GET: /api/students
    // =========================================
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    // =========================================
    // YÊU CẦU 6 (1 điểm) - CẬP NHẬT
    // POST: /api/students/update/{id}
    // =========================================
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody Student newStudent) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());

        return ResponseEntity.ok(service.updateStudent(student));
    }
}