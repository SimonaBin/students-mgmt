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

import java.util.List;

@RestController
public class StudentController {

    //method type: get      - path: /allStudents            - return: List<Student>
    //method type: post     - path: /loadStudents           - return: List<Student>
    //method type: put      - path: /update/{id}            - return: List<Student>
    //method type: get      - path: /students/{month}
    //method type: post     - path: /loadStudents           - return: List<Student>
    //method type: put      - path: /update/{id}            - return: List<Student>
    //method type: delete   - path: /student/{id}           - return: List<Student>
    //method type: delete   - path: /delete-all-students    - return: boolean

    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public List<Student> viewAllStudents() {
        return studentService.geStudentsList();
    }

    @PostMapping(value = "/loadStudents", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
    }

    @GetMapping("/students/{month}")
    public List<Student> getStudentsByMonth(@PathVariable int month) {
        return studentService.getStudentsListFilteredByMonth(month);
    }

    @DeleteMapping("/delete/{id}")
    public List<Student> deleteStudentById(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Student> updateFirstNameForStudentWithId(@PathVariable int id, @RequestBody String firstName) {
        return studentService.updateStudent(id, firstName);
    }

    @DeleteMapping("/delete-all-students")
    public boolean deleteAll(){
        return studentService.deleteAllStudents();    }



}
