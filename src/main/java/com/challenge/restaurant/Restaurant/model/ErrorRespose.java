package com.challenge.restaurant.Restaurant.model;

/**
 * Created by sachin on 4/7/19.
 */
public class ErrorRespose {

    String message;

    String details;

    public ErrorRespose(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
