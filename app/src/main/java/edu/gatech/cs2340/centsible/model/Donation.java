package edu.gatech.cs2340.centsible.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Donation {
    private Location location;
    private String title;
    private String shortDescription;
    private String longDescription;
    private double value;
    private String category;
    private User enteredBy;
    private @ServerTimestamp Date lastUpdated;


    public Donation() {

    }

    public Donation(Location location, String title, String shortDescription,
                    String longDescription, double value, String category, User enteredBy,
                    Date lastUpdated) {
        this.location = location;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.enteredBy = enteredBy;
        this.lastUpdated = lastUpdated;
    }

    public Location getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
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


    public User getEnteredBy() {
        return enteredBy;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

}
