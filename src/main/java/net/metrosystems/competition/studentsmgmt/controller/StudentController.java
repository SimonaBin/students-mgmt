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

    //method type: post             - path: /load-students           - return: List<Student> - DONE
    //method type: delete           - path: /delete-all-students    - return: boolean - DONE
    //TODO: method type: get         - path: /all-students            - return: List<Student>  - returneaza in response body toti studentii din fisier
    //TODO: method type: get         - path: /students/{month}       - return: List<Student>  - returneaza in response body toti studentii nascuti in luna {month}
    //TODO: method type: put         - path: /update/{id}            - return: List<Student> - updateaza numele studentul cu {id}, folosind valoarea preluata din request body
    //TODO: method type: delete      - path: /student/{id}           - return: List<Student> - sterge studentul din fisier cu {id}, si returneaza in responde body toate informatiile despre studentul sters

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/load-students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> loadStudentsIntoInputFile(@RequestBody List<Student> studentsList) {
        return studentService.loadStudentsIntoFile(studentsList);
    }

    @DeleteMapping("/delete-all-students")
    public boolean deleteAll(){
        return studentService.deleteAllStudents();
    }

    @GetMapping("/all-students")
    public List<Student> viewAllStudents() {
        return studentService.geStudentsList();
    }

    @GetMapping("/students/{month}")
    public List<Student> getStudentsByMonth(@PathVariable int month) {
        return studentService.getStudentsListFilteredByMonth(month);
    }

    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudentById(@PathVariable int id) {
        return studentService.removeStudent(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public List<Student> updateFirstNameForStudentWithId(@PathVariable int id, @RequestBody String firstName) {
        return studentService.updateStudent(id, firstName);
    }

}
