package com.spacex.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dragon {

    private String name;
    private boolean isActive;
    private DragonType dragonType;
    private Date firstFlight;
    private int orbitDuration;
    private String devPartner;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDragonType(DragonType dragonType){
        this.dragonType = dragonType;
    }

    public DragonType getDragonType(){
        return dragonType;
    }

    public Date getFirstFlight() {
        return firstFlight;
    }

    public void setFirstFlight(Date firstFlight) {
        this.firstFlight = firstFlight;
    }

    public int getOrbitDuration() {
        return orbitDuration;
    }

    public void setOrbitDuration(int orbitDuration) {
        this.orbitDuration = orbitDuration;
    }

    public String getDevPartner() {
        return devPartner;
    }

    public void setDevPartner(String devPartner) {
        this.devPartner = devPartner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
