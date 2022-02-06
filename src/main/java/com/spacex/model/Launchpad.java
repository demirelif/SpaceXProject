package com.spacex.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Launchpad {
    int launch_attempts;
    String name;
    String id;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getLaunch_attempts() {
        return launch_attempts;
    }
    public void setLaunch_attempts(int launch_attempts) {
        this.launch_attempts = launch_attempts;
    }
}
