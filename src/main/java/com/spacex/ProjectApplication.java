package com.spacex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.controller.ProjectController;
import com.spacex.controller.RestController;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
/**
 * The main class of the application.
 *
 * @author  Elif
 * @version 1.0
 */
@SpringBootApplication
public class ProjectApplication {
    static ProjectController projectController;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(ProjectApplication.class, args);
        RestController restController = new RestController();
        //projectController = new ProjectController();

        //List<Object> list = restController.getCrew();
        //JsonNode node = restController.getCrew("5ebf1b7323a9a60006e03a7b");
        //restController.getSuccessfulLaunches();
        restController.getTotalLaunches("5e9e4501f509094ba4566f84");
    }

}
