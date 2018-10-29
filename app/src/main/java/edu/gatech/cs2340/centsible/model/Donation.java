package edu.gatech.cs2340.centsible.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Donation {
    private String location;
    private String name;
    private String shortDescription;
    private String longDescription;
    private double value;
    private String category;
    private String enteredBy;
    private @ServerTimestamp Date lastUpdated;


    public Donation() {

    }

    public Donation(String location, String name, String shortDescription,
                    String longDescription, double value, String category, String enteredBy,
                    Date lastUpdated) {
        this.location = location;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.enteredBy = enteredBy;
        this.lastUpdated = lastUpdated;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public double getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }


    public String getEnteredBy() {
        return enteredBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

}
