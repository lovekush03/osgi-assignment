package com.assignment.demo.core.services;

import com.assignment.demo.core.models.Student;
import java.util.List;

public interface StudentClassService {
    boolean addStudent(Student student);
    boolean deleteStudent(int id);
    boolean isStudentPassed(int id);
    Student getStudent(int id);
    List<Student> getAllStudents();
}