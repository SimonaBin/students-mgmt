package net.metrosystems.competition.studentsmgmt.citrus.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

    public static final String STUDENT_REST_SERVER = "http://localhost:8092";

    @Bean
    public HttpClient RestServiceSimulator() {

        return CitrusEndpoints.http()
                .client()
                .requestUrl(STUDENT_REST_SERVER)
                .build();
    }
}
