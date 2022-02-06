package com.spacex.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Launch {
    private boolean success;
    private String failures;
    private String details;
    private Crew[] crew;
    private String[] ships;
    private String[] capsules;
    private String launchpad;
    private String flightNumber;
    private String name;
    private String dateUtc;
    private String dateUnix;
    private String dateLocal;
    private String datePrecision;
    private boolean upcoming;
    private String[] cores;
    private boolean autoUpdate;
    private boolean tbd;
    private String launchLibraryId;
    private String id;
    private String url;

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public boolean isSucceed() {
        return success == true;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFailures() {
        return failures;
    }

    public void setFailures(String failures) {
        this.failures = failures;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Crew[] getCrew() {
        return crew;
    }

    public void setCrew(Crew[] crew) {
        this.crew = crew;
    }

    public String[] getShips() {
        return ships;
    }

    public void setShips(String[] ships) {
        this.ships = ships;
    }

    public String[] getCapsules() {
        return capsules;
    }

    public void setCapsules(String[] capsules) {
        this.capsules = capsules;
    }


    public String getLaunchpad() {
        return launchpad;
    }

    public void setLaunchpad(String launchpad) {
        this.launchpad = launchpad;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateUtc() {
        return dateUtc;
    }

    public void setDateUtc(String dateUtc) {
        this.dateUtc = dateUtc;
    }

    public String getDateUnix() {
        return dateUnix;
    }

    public void setDateUnix(String dateUnix) {
        this.dateUnix = dateUnix;
    }

    public String getDateLocal() {
        return dateLocal;
    }

    public void setDateLocal(String dateLocal) {
        this.dateLocal = dateLocal;
    }

    public String getDatePrecision() {
        return datePrecision;
    }

    public void setDatePrecision(String datePrecision) {
        this.datePrecision = datePrecision;
    }

    public boolean isUpcoming() {
        return upcoming;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }

    public String[] getCores() {
        return cores;
    }

    public void setCores(String[] cores) {
        this.cores = cores;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public boolean isTbd() {
        return tbd;
    }

    public void setTbd(boolean tbd) {
        this.tbd = tbd;
    }

    public String getLaunchLibraryId() {
        return launchLibraryId;
    }

    public void setLaunchLibraryId(String launchLibraryId) {
        this.launchLibraryId = launchLibraryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
