package com.model;

public enum TimeZoneList {

    EASTERN_TIME ("US/Eastern"),
    CENTRAL_TIME ("US/CENTRAL"),
    MOUNTIAN_TIME ("US/Mountain"),
    WESTERN_TIME  ("US/Alaska"),
    PACIFIC_TIME ("Pacific/General");

    private String zone;

    TimeZoneList(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
