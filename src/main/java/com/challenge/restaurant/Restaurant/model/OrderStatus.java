package com.challenge.restaurant.Restaurant.model;

/**
 * Created by sachin on 4/7/19.
 */

public enum OrderStatus {
    PLACED("placed"),BAKING("baking"),READY_TO_DELIVER("ready to deliver");
    private final String text;

    OrderStatus(final String text){
        this.text=text;
    }



    @Override
    public String toString() {
        return text;
    }
}
