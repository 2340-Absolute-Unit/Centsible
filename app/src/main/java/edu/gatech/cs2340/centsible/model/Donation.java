package edu.gatech.cs2340.centsible.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

import androidx.annotation.NonNull;

/**
 * donation model
 */
@SuppressWarnings("ALL")
public class Donation {
    @NonNull
    private String location;
    @NonNull
    private String name;
    @NonNull
    private String shortDescription;
    @NonNull
    private String longDescription;
    private double value;
    @NonNull
    private String category;
    @NonNull
    private String enteredBy;
    @ServerTimestamp
    private Date lastUpdated;

    /**
     * empty donation constructor
     */
    public Donation() {

    }

    /**
     * constructor for donation
     *
     * @param location location of donation
     * @param name name of the donation
     * @param shortDescription short description of donation
     * @param longDescription long description of donation
     * @param value value of donation
     * @param category category of donation
     * @param enteredBy person who entered donation into app
     * @param lastUpdated when the donation was last updated
     */
    @SuppressWarnings("ConstructorWithTooManyParameters")
    public Donation(@NonNull String location, @NonNull String name,
                    @NonNull String shortDescription, @NonNull String longDescription, double value,
                    @NonNull String category, @NonNull String enteredBy, Date lastUpdated) {
        this.location = location;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.enteredBy = enteredBy;
        this.lastUpdated = (Date) lastUpdated.clone();
    }

    /**
     * getter for location of donation
     *
     * @return location where donation took place
     */
    @NonNull
    public String getLocation() {
        return location;
    }

    /**
     * getter for name of donation
     *
     * @return name of donation
     */
    @SuppressWarnings("TypeMayBeWeakened")
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * getter for short description of donation
     *
     * @return short description of donation
     */
    @SuppressWarnings("TypeMayBeWeakened")
    @NonNull
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * getter for long description of donation
     *
     * @return long description of donation
     */
    @SuppressWarnings("TypeMayBeWeakened")
    @NonNull
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * getter for value of donation
     *
     * @return value of donation
     */
    public double getValue() {
        return value;
    }

    /**
     * getter for category of donation
     *
     * @return category of donation
     */
    @SuppressWarnings("TypeMayBeWeakened")
    @NonNull
    public String getCategory() {
        return category;
    }

    /**
     * getter for person who entered the donation
     *
     * @return person who entered the donation
     */
    @NonNull
    public String getEnteredBy() {
        return enteredBy;
    }

    /**
     * getter for when donation was last updated
     *
     * @return date of when donation was last updated
     */
    public Date getLastUpdated() {
        return (Date) lastUpdated.clone();
    }

}
