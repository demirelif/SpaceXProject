package com.spacex.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Crew {
    private String[] launches;
    public String[] getLaunches() {
        return launches;
    }
    public void setLaunches(String[] launches) {
        this.launches = launches;
    }
}
