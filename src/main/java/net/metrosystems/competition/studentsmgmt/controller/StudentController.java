package net.metrosystems.competition.studentsmgmt.controller;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public List<Student> viewAllStudents(HttpServletRequest request) {
        return studentService.geStudentsList();
    }

    @PostMapping(value = "/loadStudents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody(required = true) List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
    }

    @GetMapping("/students/{month}")
    public List<Student> getStudentsByMonth(@PathVariable int month, HttpServletRequest request) {
        return studentService.geStudentsListFilteredByMonth(month);
    }

    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudentById(@PathVariable int id, HttpServletRequest request) {
        return studentService.removeStudent(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> updateFirstNameForStudentWithId(@PathVariable int id, @RequestBody String firstName) {
        return studentService.updateStudent(id, firstName);
    }
}
