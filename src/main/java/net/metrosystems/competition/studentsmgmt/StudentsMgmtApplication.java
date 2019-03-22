package net.metrosystems.competition.studentsmgmt;

import net.metrosystems.competition.studentsmgmt.controller.StudentController;
import net.metrosystems.competition.studentsmgmt.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = {
		StudentController.class,
		StudentService.class
})
@SpringBootApplication
public class StudentsMgmtApplication {

	public static void main(String... args) {
		SpringApplication.run(StudentsMgmtApplication.class, args);
	}

}
