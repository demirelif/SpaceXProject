package com.spacex.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Launchpad {
    String launch_attempts;
    String name;
    String full_name;

    public String getFull_name(){
        return full_name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLaunchAttempts() {
        return launch_attempts;
    }

    public void setLaunchAttempts(String launchAttempts) {
        this.launch_attempts = launchAttempts;
    }



}
