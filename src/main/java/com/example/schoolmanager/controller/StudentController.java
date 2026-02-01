package com.example.schoolmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;

    // ===============================
    // ➕ THÊM SINH VIÊN (JSON - FETCH)
    // ===============================
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("❌ Tên sinh viên không được để trống");
        }

        Student saved = service.addStudent(student);
        return ResponseEntity.ok(saved);
    }

    // ==================================
    // ➕ THÊM SINH VIÊN (FORM HTML)
    // ==================================
    @PostMapping("/add")
    public ResponseEntity<?> addStudentForm(
            @RequestParam String name,
            @RequestParam(required = false) String email) {

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        return ResponseEntity.ok(service.addStudent(student));
    }

    // ===============================
    // ✏️ SỬA SINH VIÊN
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody Student newStudent) {

        Student student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Không tìm thấy sinh viên với ID = " + id);
        }

        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());

        return ResponseEntity.ok(service.addStudent(student));
    }

    // ===============================
    // ❌ XÓA SINH VIÊN
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        Student student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Không tìm thấy sinh viên với ID = " + id);
        }

        service.deleteStudent(id);
        return ResponseEntity.ok("✅ Đã xóa: " + student.getName());
    }

    // ===============================
    // 🔍 TÌM KIẾM
    // ===============================
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam String name) {
        return service.findByName(name);
    }

    // ===============================
    // 📋 DANH SÁCH
    // ===============================
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    // ===============================
    // 📌 GET BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        Student student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Không tìm thấy sinh viên với ID = " + id);
        }

        return ResponseEntity.ok(student);
    }
}
