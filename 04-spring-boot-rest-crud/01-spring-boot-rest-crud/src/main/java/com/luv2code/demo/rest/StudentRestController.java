package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //Define @PostConstruct to load student data... only once!
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));

    }
    // Define endpoint for "/studets" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    // Define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // check the studentId again list size
        if(studentId >= theStudents.size() || (studentId<0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

}
