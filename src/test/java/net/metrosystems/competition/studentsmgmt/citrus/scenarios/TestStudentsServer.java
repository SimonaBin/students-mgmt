package net.metrosystems.competition.studentsmgmt.citrus.scenarios;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import net.metrosystems.competition.studentsmgmt.citrus.CTBaseScenarios;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({CitrusExtension.class})
public class TestStudentsServer extends CTBaseScenarios {
    public static final String PATH_ALL_STUDENTS = "/allStudents";
    public static final String PATH_LOAD_STUDENTS = "/loadStudents";
    public static final String PATH_RESPONSE_ALL_STUDENTS = "test_data/allStudents.json";

    public static final String PATH_ONE_STUDENT = "test_data/oneStudent.json";

    @Test
    @CitrusTest
    void testGetAllStudents(@CitrusResource TestRunner runner) {
        testGetEndPointForStudentServer(runner, PATH_ALL_STUDENTS, PATH_RESPONSE_ALL_STUDENTS);
    }

    @Test
    @CitrusTest
    void testPostStudent(@CitrusResource TestRunner runner) {
        testPostEndPointForStudentServer(runner, PATH_LOAD_STUDENTS, PATH_ONE_STUDENT, PATH_ONE_STUDENT);
    }

}
