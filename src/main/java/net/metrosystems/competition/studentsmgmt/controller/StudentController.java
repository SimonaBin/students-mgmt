package net.metrosystems.competition.studentsmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.StudentService;


@RestController
public class StudentController {

	@Autowired 
	private StudentService studentService;
	
	@RequestMapping(value = "/allStudents", method = RequestMethod.GET)
    public List<Student> viewAllStudents(HttpServletRequest request) {
        return studentService.geStudentsList();
	}
	
	@RequestMapping(value = "/loadStudents", method = RequestMethod.POST)
    public List<Student> loadStudentsIntoImputFile(@RequestBody(required = true) List<Student> studentsList) {
		System.out.println("/loadStudents");
        return studentService.loadStudentsIntoFile(studentsList);
	}
}
