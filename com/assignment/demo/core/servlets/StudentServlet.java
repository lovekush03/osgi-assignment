package com.assignment.demo.core.servlets;

import com.assignment.demo.core.services.StudentClassService;
import com.assignment.demo.core.models.Student;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/studentInfo",
                "sling.servlet.methods=GET"
        }
)
public class StudentServlet extends SlingAllMethodsServlet {

    @Reference
    private StudentClassService studentClassService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentClassService.getAllStudents();
//
//        response.setContentType("application/json");
//        response.getWriter().write("[");
//        for (int i = 0; i < students.size(); i++) {
//            Student s = students.get(i);
//            response.getWriter().write("{ \"id\": " + s.getId() + ", \"name\": \"" + s.getName() + "\", \"marks\": " + s.getMarks() + ", \"age\": " + s.getAge() + "}");
//            if (i < students.size() - 1) response.getWriter().write(",");
//        }
//        response.getWriter().write("]");

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String marksObtained = request.getParameter("marks");

        switch (action){
            case "1":
                //call addStudent service
                //if incomplete data passed from queryParameter
                if(id == null || age == null || name == null || marksObtained == null){
                    response.getWriter().write("Incomplete Student data Passed");
                }
                int studentId = Integer.parseInt(id);
                int studentAge = Integer.parseInt(age);
                int studentMarks = Integer.parseInt(marksObtained);

                //Create new Student from query Parameter data
                Student newStudent = new Student(studentId,name,studentAge,studentMarks);
                studentClassService.addStudent(newStudent);
                response.getWriter().write("Student added Successfully");
                break;

            case "2":
                //call deleteStudent service
                //if id not passed
                if(id == null) response.getWriter().write("Incomplete Student Data Passed");
                int studId = Integer.parseInt(id);
                studentClassService.deleteStudent(studId);
                response.getWriter().write("Student Deleted Successfully");
                break;

            case "3":
                //call isStudentPassed service
                //if id not passed
                if(id == null) response.getWriter().write("Incomplete Student Data Passed");
                int stuId = Integer.parseInt(id);
                boolean isPresent = studentClassService.isStudentPassed(stuId);
                if(isPresent) response.getWriter().write("Student Passed");
                else response.getWriter().write("Student Failed !!!");
                break;

            case "4":
                //call getStudent(id) service
                //if id not passed
                if(id == null) response.getWriter().write("Incomplete Student Data Passed");
                int Id = Integer.parseInt(id);
                Student requiredStudent = studentClassService.getStudent(Id);
                response.getWriter().write("{ \"id\": " + requiredStudent.getId() + ", \"name\": \"" + requiredStudent.getName() + "\", \"marks\": " + requiredStudent.getMarks() + ", \"age\": " + requiredStudent.getAge() + "}");
                break;

            case "5":
                //call getAllStudents() service
                response.getWriter().write("[");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            response.getWriter().write("{ \"id\": " + s.getId() + ", \"name\": \"" + s.getName() + "\", \"marks\": " + s.getMarks() + ", \"age\": " + s.getAge() + "}");
            if (i < students.size() - 1) response.getWriter().write(",");
        }
        response.getWriter().write("]");
                break;

            default:
                response.getWriter().write("Entry Valid Query Action");
        }




    }
}
