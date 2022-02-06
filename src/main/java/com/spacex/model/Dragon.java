package com.spacex.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dragon {
    private DragonType dragonType;
    private int orbitDuration;


    public DragonType getDragonType(){
        return dragonType;
    }
    public void setDragonType(DragonType dragonType){
        this.dragonType = dragonType;
    }
    public int getOrbitDuration() {
        return orbitDuration;
    }
    public void setOrbitDuration(int orbitDuration){
        this.orbitDuration = orbitDuration;
    }


}
