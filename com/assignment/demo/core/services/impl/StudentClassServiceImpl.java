package com.assignment.demo.core.services.impl;

import com.assignment.demo.core.services.StudentClassService;
import com.assignment.demo.core.services.ClassConfigurationService;
import com.assignment.demo.core.models.Student;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component(service = StudentClassService.class, immediate = true)
public class StudentClassServiceImpl implements StudentClassService {

    @Reference
    private ClassConfigurationService classConfigurationService;

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(101,"Lovepreet Singh",82,22),
            new Student(102,"Harpreet Singh",80,23),
            new Student(102,"Damanpreet Singh",90,21)
    ));

    @Override
    public boolean addStudent(Student student) {
        if (!classConfigurationService.isClassLimitReached(students.size())) {
            return students.add(student);
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    @Override
    public boolean isStudentPassed(int id) {
        Student student = getStudent(id);
        return student != null && student.getMarks() >= classConfigurationService.getPassingMarks();
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> student = students.stream().filter(s -> s.getId() == id).findFirst();
        return student.orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }
}