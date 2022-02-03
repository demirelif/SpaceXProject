package com.spacex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.spacex.model.*;
import com.sun.source.doctree.StartElementTree;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class implements the required calculations
 * for pre-determined cases.
 *
 * @author  Elif
 * @version 1.0
 */
public class ProjectService {

    /**
     * This method calculates the total time in space of all
     * crew-dragon fights.
     * @param dragons
     * @return Total time in int.
     */
    public int calculateTotalTime(ArrayList<Dragon> dragons){
        int totalTime = 0;
        for ( int i = 0; i < dragons.size(); i++ ){
            if ( dragons.get(i).getDragonType() == DragonType.CREW ){
                totalTime += (dragons.get(i)).getOrbitDuration();
            }
        }
        return totalTime;
    }

    /**
     * This method calculates the total number of launches
     * from the given launchpad.
     */
    public int calculateTotalLaunces(Launchpad launchpad){
        return Integer.parseInt(launchpad.getLaunchAttempts());
    }

    /**
     * This method finds the successful launches.
     */
    public ArrayList<Launch> getSuccessfulLaunches(ArrayList<Launch> launches){
        ArrayList<Launch> successfulLaunches = new ArrayList<>();
        for ( int i = 0; i < launches.size(); i++ ){
            if ( launches.get(i).isSucceed() ){
                successfulLaunches.add(launches.get(i));
            }
        }
        return successfulLaunches;
    }

    /**
     * This method finds the unsuccessful launches.
     */
    public ArrayList<Launch> getUnsuccessfulLaunches(ArrayList<Launch> launches){
        ArrayList<Launch> unsuccessfulLaunches = new ArrayList<>();
        for ( int i = 0; i < launches.size(); i++ ){
            if ( !(launches.get(i).isSucceed() )){
                unsuccessfulLaunches.add(launches.get(i));
            }
        }
        return unsuccessfulLaunches;
    }
}
