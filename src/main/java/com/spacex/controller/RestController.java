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

    public JsonNode getLaunchDetails(@RequestParam String id) throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        String requestUrl = url + "/v4/launches/{id}";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        String urlTemplate = UriComponentsBuilder.fromUriString(requestUrl).buildAndExpand(params).toUriString();
        HttpEntity<String> response = restTemplate.getForEntity(urlTemplate, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        String url = jsonObject.getString("details");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(url);
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

    public JsonNode getCompanyInfo() throws JSONException, JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/company", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    public JsonNode getNextLaunch() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches/next", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

    public JsonNode getLatestLaunch() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches/latest", String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;
    }

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

    public List<String> getLaunchpadNames() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launchpads", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
       // ArrayList<Launchpad> launchpads = new ArrayList<>();
        ArrayList<String> launchpads = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String launchpad = jsonArray.getJSONObject(i).getString("name");
         //   Launchpad launchpad = new Launchpad();
         //   launchpad.setName(jsonArray.getJSONObject(i).getString("name"));
            launchpads.add(launchpad);
            System.out.println("launchpad : " + launchpad );
        }
        return launchpads;
    }

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

    public List<Launch> getLaunchList() throws JsonProcessingException, JSONException {
        restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/v4/launches", String.class);
        JSONArray jsonArray = new JSONArray(response.getBody());
        ArrayList<Launch> launches = new ArrayList<>();
        int i = 0; // additional counter to decrease the data size for visual reasons
        while ( i < 5 ){
            Launch launch = new Launch();
            launch.setId(jsonArray.getJSONObject(i).getString("id"));
            launch.setUrl(jsonArray.getJSONObject(i).getJSONObject("links").getJSONObject("patch").getString("small"));
            launch.setName(jsonArray.getJSONObject(i).getString("name"));
            launches.add(launch);
            i++;
        }
        return launches;
    }

    public JsonNode getLaunchPatch() throws JsonProcessingException {
        String url = "";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(url));
        return root;
    }
}
