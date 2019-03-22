package net.metrosystems.competition.studentsmgmt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.metrosystems.competition.studentsmgmt.dto.Student;
import net.metrosystems.competition.studentsmgmt.util.StudentUtil;

@Service
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	private static final String FILE_NAME = "resource-data.csv";

	public List<Student> geStudentsList() {
		try {
			StudentUtil su = new StudentUtil();
			return su.readData(FILE_NAME);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to get students list!!", e.getMessage());
		}
		return Collections.emptyList();
	}

	public List<Student> loadStudentsIntoFile(List<Student> students) {
		try {
			StudentUtil su = new StudentUtil();
			su.writeDataToCSV(FILE_NAME, students);
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to write into file!!", e.getMessage());
		}
		return students;
	}

	public List<Student> geStudentsListFilteredByMonth(int month) {
		List<Student> allStudents = geStudentsList();

		return allStudents.stream()
					.filter(s -> s.getDateOfBirth().getMonth().getValue() == month)
					.collect(Collectors.toList());
	}

	public List<Student> removeStudent(int id) {
		List<Student> allStudents = geStudentsList();

		try {
			StudentUtil su = new StudentUtil();
			su.writeDataToCSV(FILE_NAME, allStudents.stream().filter(s -> s.getId() != id).collect(Collectors.toList()));
		} catch (Exception e) {
			LOGGER.error("Error {} while trying to write into file!!", e.getMessage());
		}

		return allStudents.stream()
						.filter(s -> s.getId() == id)
						.collect(Collectors.toList());
	}

	public List<Student> updateStudent(int id, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
