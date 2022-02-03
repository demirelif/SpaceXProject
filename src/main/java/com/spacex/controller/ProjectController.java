package com.spacex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.model.Launch;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
/**
 * This class communicate with the localhost and
 * implements REST-API requirements.
 *
 * @author  Elif
 * @version 1.0
 */
@RestController
public class ProjectController {
    private final com.spacex.controller.RestController controller;
    public ProjectController(com.spacex.controller.RestController controller){
        this.controller = controller;
    }

    @GetMapping("/total-people-in-space")
    //public JsonNode getCrew() throws JsonProcessingException {
    public JsonNode getTotalPeople() throws JsonProcessingException, JSONException {
        //return controller.getTotalPeople();
        return controller.getTotalPeople();
    }

    @GetMapping("/crew")
    //public JsonNode getCrew() throws JsonProcessingException {
    public JsonNode getCrew() throws JsonProcessingException {
        return controller.getCrew();
    }

    @GetMapping("/find-crew")
    public JsonNode getCrew(@RequestParam String id ) throws JsonProcessingException {
        return controller.getCrew(id);
    }

    @GetMapping("/successful")
    public List<Launch> getSuccessfulLaunches() throws IOException, JSONException {
        return controller.getSuccessfulLaunches();
    }

    @GetMapping("/total-time")
    public JsonNode getTotalTime() throws JSONException, JsonProcessingException {
        return controller.getTotalTime();
    }

    @GetMapping("/total-launches")
    public JsonNode getTotalLaunches(String id) throws JSONException, JsonProcessingException {
        return controller.getTotalLaunches(id);
    }

    @GetMapping("/company-info")
    public JsonNode getCompanyInfo() throws JSONException, JsonProcessingException {
        return controller.getCompanyInfo();
    }




}
