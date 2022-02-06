package com.spacex.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Launch {
    private boolean success;
    public boolean isSucceed() {
        return success == true;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
