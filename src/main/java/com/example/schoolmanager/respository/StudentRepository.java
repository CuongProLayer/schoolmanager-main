package com.example.schoolmanager.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schoolmanager.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Search sinh viên theo tên (không phân biệt hoa thường)
    List<Student> findByNameContainingIgnoreCase(String name);
}

