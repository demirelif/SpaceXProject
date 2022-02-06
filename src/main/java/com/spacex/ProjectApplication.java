package com.spacex;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
/**
 * The main class of the application.
 *
 * @author  Elif
 * @version 1.0
 */
@SpringBootApplication
public class ProjectApplication {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
