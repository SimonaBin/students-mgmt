package net.metrosystems.competition.studentsmgmt.citrus;

import com.consol.citrus.annotations.CitrusEndpoint;
import com.consol.citrus.dsl.junit.jupiter.CitrusExtension;
import com.consol.citrus.dsl.runner.TestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.util.FileUtils;
import net.metrosystems.competition.studentsmgmt.StudentsMgmtApplication;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                StudentsMgmtApplication.class
        })
@ExtendWith({CitrusExtension.class})
public class CTBaseScenarios {

    @CitrusEndpoint
    HttpClient studentHttpClient;

    protected void testGetEndPointForStudentServer(TestRunner runner, String clientPath, String resultPath) {
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

    protected void testPostEndPointForStudentServer(TestRunner runner, String clientPath, String requestBody, String resultPath) {
        runner.http(action -> action
                .client(studentHttpClient)
                .send()
                .post(clientPath)
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .payload(new ClassPathResource(requestBody))
               // .payload("[ { \"id\": 678, \"firstName\": \"Vasile\", \"lastName\": \"Mihai\", \"dateOfBirth\": \"15/11/1989\", \"spec\": \"chineza\", \"avg\": 5.73 } ]")
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
