package net.metrosystems.competition.studentsmgmt.citrus;

import com.consol.citrus.annotations.CitrusEndpoint;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import net.metrosystems.competition.studentsmgmt.StudentsMgmtApplication;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                StudentsMgmtApplication.class
        })
public class CTBaseScenarios {

    @CitrusEndpoint
    HttpClient studentHttpClient;

    @BeforeAll
    private void startApp(){
        SpringApplication.run(StudentsMgmtApplication.class, "");
    }

    protected void deleteContentFromCSV(TestRunner runner, String clientPath){
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .delete(clientPath)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT));
    }

    protected void callEndpointGetAllStudents(TestRunner runner, String clientPath, String resultPath) {
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .get(clientPath)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .payload(new ClassPathResource(resultPath)));
               // .extractFromPayload("$.[*]", "response"));

        //runner.echo("${response}");
    }

    protected void callEndpointLoadStudents(TestRunner runner, String clientPath, String requestBody, String resultPath) {
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .post(clientPath)
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .payload(new ClassPathResource(requestBody))
                );

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .extractFromPayload("$.[*]", "response")
                .payload(new ClassPathResource(resultPath)));
        runner.echo("${response}");
    }

    protected void callUpdateStudentName(TestRunner runner, String clientPath, String idStudent, String studentName, String resultPath){
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .put(String.format(clientPath, idStudent))
                .messageType(MessageType.PLAINTEXT)
                .payload(studentName)
        );

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .extractFromPayload("$.[*]", "response")
                .payload(new ClassPathResource(resultPath)));
        runner.echo("${response}");
    }

    protected void callDeleteStudent(TestRunner runner, String clientPath, String idStudent, String resultPath){
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .delete(String.format(clientPath, idStudent))
        );

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .extractFromPayload("$.[*]", "response")
                .payload(new ClassPathResource(resultPath)));
        runner.echo("${response}");
    }


    protected void callGetAllStudentsWhichAreBornInMonth(TestRunner runner, String clientPath, String month, String resultPath){
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .get(String.format(clientPath, month))
        );

        runner.http(action -> action
                .client(studentHttpClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .extractFromPayload("$.[*]", "response")
                .payload(new ClassPathResource(resultPath)));
        runner.echo("${response}");
    }

}
