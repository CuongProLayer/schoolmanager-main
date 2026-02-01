package com.example.schoolmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // ✅ Thêm sinh viên
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    // ✅ Cập nhật sinh viên
    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    // ✅ Xóa sinh viên
    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    // ✅ Tìm theo tên
    public List<Student> findByName(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }

    // ✅ Lấy tất cả
    public List<Student> getAll(){
        return repository.findAll();
    }

    // ✅ Lấy theo ID
    public Student getStudentById(int id){
        return repository.findById(id).orElse(null);
    }
}
