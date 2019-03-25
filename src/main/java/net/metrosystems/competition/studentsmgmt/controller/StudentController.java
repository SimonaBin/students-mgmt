package net.metrosystems.competition.studentsmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public List<Student> viewAllStudents() {
        return studentService.geStudentsList();
    }

    @PostMapping(value = "/loadStudents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody(required = true) List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
    }

    @GetMapping("/students/{month}")
    public List<Student> getStudentsByMonth(@PathVariable int month) {
        return studentService.geStudentsListFilteredByMonth(month);
    }

    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudentById(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Student> updateFirstNameForStudentWithId(@PathVariable int id, @RequestBody String firstName) {
        return studentService.updateStudent(id, firstName);
    }
}
