package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLine(StudentDAO studentDAO){
		return runner ->{
		//createStudent(studentDAO);
		createMultipleStudent(studentDAO);
		//readStudent(studentDAO);
		//queryForStudents(studentDAO);
		//queryForStudentsByLastName(studentDAO);
		//updateStudent(studentDAO);
		//deleteStudent(studentDAO);
		//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Delete Student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//Retrieve student based on the id: primary key
		int studentid = 1;
		System.out.println("Getting student with id:" + studentid);
		Student myStudent = studentDAO.findById(studentid);

		//change first name
		System.out.println("Updating student...");
		myStudent.setFirstName("John");

		//update
		studentDAO.update(myStudent);

		//display
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of student
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		System.out.println("saving the student...");
		studentDAO.save(tempStudent);

		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		//Retrieve Student based on id : primary key
		System.out.println("Retrieving student with Id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		System.out.println("Found the student" + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		System.out.println("Creating 3 student object...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		//save the student object
		System.out.println("saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		//display id of saved student
		System.out.println("Saved student1. Generated id: " + tempStudent1.getId());
		System.out.println("Saved student2. Generated id: " + tempStudent2.getId());
		System.out.println("Saved student3. Generated id: " + tempStudent3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		//save the student object
		System.out.println("saving the student...");
		studentDAO.save(tempStudent);

		//display id of saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}
}
