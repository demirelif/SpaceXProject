package com.spacex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.model.Launch;
import com.spacex.model.Launchpad;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin(origins = "http://localhost:3000",allowedHeaders = "*")
@RestController
public class ProjectController {
    private final com.spacex.controller.RestController controller;
    public ProjectController(com.spacex.controller.RestController controller){
        this.controller = controller;
    }

    @GetMapping("/total-people-in-space")
    public JsonNode getTotalPeople() throws JsonProcessingException, JSONException {
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


    @GetMapping("/total-number-of-launches-from-launchpad")
    public JsonNode getTotalNumberOfLaunchesFromLaunchpad(@RequestParam String id) throws JSONException, JsonProcessingException {
       // String idx = "5eb87cdaffd86e000604b32b";

        return controller.getTotalNumberOfLaunchesFromLaunchpad(id);
    }

    @GetMapping("/company-info")
    public JsonNode getCompanyInfo() throws JSONException, JsonProcessingException {
        return controller.getCompanyInfo();
    }

    @GetMapping("/no-of-successful-launches")
    public JsonNode getNoOfSuccessfulLaunches() throws IOException, JSONException {
        return controller.getNoOfSuccessfulLaunches();
    }
    @GetMapping("/no-of-unsuccessful-launches")
    public JsonNode getNoOfUnsuccessfulLaunches() throws IOException, JSONException {
        return controller.getNoOfUnsuccessfulLaunches();
    }

    @GetMapping("/next-launch")
    public JsonNode getNextLaunch() throws JsonProcessingException, JSONException {
        return controller.getNextLaunch();
    }

    @GetMapping("/latest-launch")
    public JsonNode getLatestLaunch() throws JsonProcessingException, JSONException {
        return controller.getLatestLaunch();
    }

    @GetMapping("/average-mass-of-rockets")
    public JsonNode getAverageMassOfRockets() throws JsonProcessingException, JSONException {
        return controller.getAverageMassOfRockets();
    }

    @GetMapping("/launchpadNames")
    public List<String> getLaunchpadNames() throws JsonProcessingException, JSONException {
        return controller.getLaunchpadNames();
    }

    @GetMapping("/launch-patch")
    public JsonNode getLaunchPatch() throws JsonProcessingException, JSONException {
        return controller.getLaunchPatch();
    }

    @GetMapping("/launchpads")
    public List<Launchpad> getLaunchpads() throws JsonProcessingException, JSONException {
        return controller.getLaunchpads();
    }

}
