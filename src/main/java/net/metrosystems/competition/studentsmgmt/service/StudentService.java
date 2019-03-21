package net.metrosystems.competition.studentsmgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.service.util.StudentUtil;

@Service
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final String fileName = "resource-data.csv";
    
	public List<Student> geStudentsList(){
		List<Student> studentsList = new ArrayList<>(); 
		try {
			StudentUtil su = new StudentUtil();
			studentsList= su.readData(fileName);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to get students list!!", e.getMessage());
		}
		return studentsList;
	}

	public List<Student> loadStudentsIntoFile(List<Student> students) {
		try {
			for(Student s: students)
				LOGGER.info(s.toString());
			StudentUtil su = new StudentUtil();
			su.writeData(fileName, students);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to write into file!!", e.getMessage());
		}
		return null;
	}
	
}
