package com.spacex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.model.*;
import com.spacex.service.ProjectService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * This class obtains the required information from
 * SpaceX API
 * @author  Elif
 * @version 1.0
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {
    // @Autowired
    private RestTemplate restTemplate;
    final static String url = "https://api.spacexdata.com";

    /**
     * This method returns the company information such as
     * summary, ceo, cto.
     * @return JsonNode contains company information
     */
    public JsonNode getCompanyInfo() throws JSONException, JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/company", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    /**
     * This method returns the total number of successful launches
     * @return a JsonNode contains number of successful launches
     */
    public JsonNode getNoOfSuccessfulLaunches() throws IOException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        int launchCounter = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("success").equals("true")) {
                launchCounter++;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(launchCounter));
        return root;
    }

    /**
     * This method returns the total number of unsuccessful launches
     * @return a JsonNode contains number of unsuccessful launches
     */
    public JsonNode getNoOfUnsuccessfulLaunches() throws IOException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        int launchCounter = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("success").equals("false")) {
                launchCounter++;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(launchCounter));
        return root;
    }

    /**
     * This method returns the total number of people
     * in the crew.
     * @return a JsonNode contains the size of the crew
     */
    public JsonNode getTotalPeople() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/crew", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(jsonArray.length()));
        return root;
    }

    /**
     * This method calculates and returns the total orbit duration
     * (in years) of crew-dragons.
     * @return a JsonNode contains the total time
     */
    public JsonNode getTotalTime() throws JSONException, JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/dragons", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        int time = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            if (!(jsonArray.getJSONObject(i).getString("crew_capacity").equals("0"))) {
                time += Integer.parseInt(jsonArray.getJSONObject(i).getString("orbit_duration_yr"));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(time));
        return root;
    }

    /**
     * This method calculates and returns the
     * average mass of all the rockets
     * @return a JsonNode contains the average mass
     */
    public JsonNode getAverageMassOfRockets() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/rockets", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        long totalMass = 0;
        int noOfRockets = jsonArray.length();
        for (int i = 0; i < jsonArray.length(); i++) {
            totalMass += Integer.parseInt(jsonArray.getJSONObject(i).getJSONObject("mass").getString("kg"));
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(totalMass/noOfRockets));
        return root;
    }

    /**
     * This method returns a list of Launchpad objects
     * created from the data
     * @return a list of launchpad objects
     */
    public List<Launchpad> getLaunchpads() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launchpads", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        ArrayList<Launchpad> launchpads = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Launchpad launchpad = new Launchpad();
            launchpad.setName(jsonArray.getJSONObject(i).getString("name"));
            launchpad.setId(jsonArray.getJSONObject(i).getString("id"));
            launchpads.add(launchpad);
        }
        return launchpads;
    }

    /**
     * This method returns the total number of
     * launches from the launchpad with
     * the given id
     * @param id of the launchpad
     * @return a JsonNode contains the number
     */
    public JsonNode getTotalNumberOfLaunchesFromLaunchpad(@RequestParam String id) throws JSONException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        String requestUrl = url + "/v4/launchpads/{id}";
        //String requestUrl = url + "/v4/launches/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        String urlTemplate = UriComponentsBuilder.fromUriString(requestUrl).buildAndExpand(params).toUriString();
        System.out.println(urlTemplate);
        HttpEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        Launchpad launchpad = objectMapper.readValue(response.getBody(), Launchpad.class);
        System.out.println(response);
        System.out.println(launchpad.getLaunch_attempts());
        return objectMapper.readTree(String.valueOf(launchpad.getLaunch_attempts()));
    }

    /**
     * This method returns the latest launch
     * @return a JsonNode contains the latest launch
     */
    public JsonNode getLatestLaunch() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches/latest", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    /**
     * This method returns the next launch
     * @return a JsonNode contains the next launch
     */
    public JsonNode getNextLaunch() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches/next", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }
}
