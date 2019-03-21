package net.metrosystems.competition.studentsmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/loadStudents", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoImputFile(@RequestBody(required = true) List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
	}
	
	@RequestMapping(value = "/students/{month}", method = RequestMethod.GET)
    public List<Student> getStudentsByMonth(@PathVariable int month, HttpServletRequest request) {
        return studentService.geStudentsListFilteredByMonth(month);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public List<Student> deleteStudentById(@PathVariable int id, HttpServletRequest request) {
        return studentService.removeStudent(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> updateFirstnameForStudentWithId(@PathVariable int id, @RequestBody(required = true) String firstName) {
        return studentService.updateStudent(id, firstName);
	}
}
