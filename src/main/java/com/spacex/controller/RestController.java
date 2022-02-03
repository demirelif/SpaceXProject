package com.spacex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.model.*;
import com.spacex.service.ProjectService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
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
    private ProjectService projectService;
    final static String url = "https://api.spacexdata.com";


    public JsonNode getCrew() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/crew", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    public JsonNode getCrew(@RequestParam String id) throws JsonProcessingException {
        restTemplate = new RestTemplate();
        String requestUrl = url + "/v4/crew/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        String urlTemplate = UriComponentsBuilder.fromUriString(requestUrl).buildAndExpand(params).toUriString();
        HttpEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    public List<Launch> getSuccessfulLaunches() throws IOException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        List<Launch> launches = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getString("success").equals("true")) {
                Launch launch = new Launch();
                launch.setName(jsonArray.getJSONObject(i).getString("name"));
                launches.add(launch);
            }
        }
        return launches;
    }

    public JsonNode getTotalPeople() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/crew", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(jsonArray.length()));
        return root;
    }

    public JsonNode getTotalTime() throws JSONException, JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/dragons", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        int time = 0;
        System.out.println(" LENGHT " + jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            if (!(jsonArray.getJSONObject(i).getString("crew_capacity").equals("0"))) {
                time += Integer.parseInt(jsonArray.getJSONObject(i).getString("orbit_duration_yr"));
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(time));
        return root;
    }

    public JsonNode getTotalLaunches(@RequestParam String id) throws JSONException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        String requestUrl = url + "/v4/launchpads/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        String urlTemplate = UriComponentsBuilder.fromUriString(requestUrl).buildAndExpand(params).toUriString();
        HttpEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        Launchpad launchpad = objectMapper.readValue(response.getBody(), Launchpad.class);
        return objectMapper.readTree(String.valueOf(launchpad.getLaunchAttempts()));
    }

    public JsonNode getCompanyInfo() throws JSONException, JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/company", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }
}
