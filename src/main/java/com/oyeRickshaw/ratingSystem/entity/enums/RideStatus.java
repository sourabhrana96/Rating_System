package com.oyeRickshaw.ratingSystem.entity.enums;

/**
 * @author sourabhrana
 */

public enum RideStatus {

    ON_GOING(0, "ON_GOING"),
    COMPLETED(1, "COMPLETED"),
    CANCELLED(2, "CANCELLED");


    Integer value;
    String type;

    RideStatus(Integer value, String type) {
        this.value = value;
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
