package net.metrosystems.competition.studentsmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "net.metrosystems.competition.studentsmgmt",
		"net.metrosystems.competition.studentsmgmt.controller", "net.metrosystems.competition.studentsmgmt.service" })
@SpringBootApplication
public class StudentsMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsMgmtApplication.class, args);
	}

}
