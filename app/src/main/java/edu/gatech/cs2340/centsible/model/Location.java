package edu.gatech.cs2340.centsible.model;

import java.io.Serializable;

/**
 * location information model
 */
public class Location implements Serializable {
    private String key = "N/A";
    private String name = "N/A";
    private String latitude = "N/A";
    private String longitude = "N/A";
    private String stAddress = "N/A";
    private String city = "N/A";
    private String state = "N/A";
    private String zip = "N/A";
    private String type = "N/A";
    private String phone = "N/A";
    private String website = "N/A";

    /*empty constructor for location
    public Location() {

    }*/

    /**
     * setter for key of location
     *
     * @param inKey key for location
     */
    public void setKey(String inKey) {
        this.key = inKey;
    }

    /**
     * getter for key of location
     *
     * @return key of location
     */
    public String getKey() {
        return this.key;
    }

    /**
     * setter for name of location
     *
     * @param inName name of location
     */
    public void setName(String inName) {
        this.name = inName;
    }

    /**
     * getter for name of location
     *
     * @return name of location
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter for latitude of location
     *
     * @param inLat latitude of location
     */
    public void setLatitude(String inLat) {
        this.latitude = inLat;
    }

    /**
     * getter for latitude of location
     *
     * @return latitude of location
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * setter for longitude of location
     *
     * @param inLong longitude of location
     */
    public void setLongitude(String inLong) {
        this.longitude = inLong;
    }

    /**
     * getter for longitude of location
     *
     * @return longitude of location
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * setter for location street address
     *
     * @param inAdd stress address of location
     */
    public void setStAddress(String inAdd) {
        this.stAddress = inAdd;
    }

    /**
     * getter for street address of location
     *
     * @return st address of location
     */
    public String getStAddress() {
        return this.stAddress;
    }

    /**
     * setter for city of location
     *
     * @param inCity city where the location is
     */
    public void setCity(String inCity) {
        this.city = inCity;
    }

    /**
     * getter for city of location
     *
     * @return city of location
     */
    public String getCity() {
        return this.city;
    }

    /**
     * getter for set state of location
     *
     * @param inState state of location
     */
    public void setState(String inState) {
        this.state = inState;
    }

    /**
     * getter for state of location
     *
     * @return state of location
     */
    public String getState() {
        return this.state;
    }

    /**
     * setter for zip code of location
     *
     * @param inZip zip code of location
     */
    public void setZip(String inZip) {
        this.zip = inZip;
    }

    /**
     * getter for zip code of location
     *
     * @return zip code of location
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * setter for type of location
     *
     * @param inTyp type of location
     */
    public void setType(String inTyp) {
        this.type = inTyp;
    }

    /**
     * getter for type of location
     *
     * @return type of location
     */
    public String getType() {
        return this.type;
    }

    /**
     * setter for phone number of location
     *
     * @param inPho phone number of location
     */
    public void setPhone(String inPho) {
        this.phone = inPho;
    }

    /**
     * getter for phone number of location
     *
     * @return phone number of location
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * setter for website of location
     *
     * @param inWeb website of location
     */
    public void setWebsite(String inWeb) {
        this.website = inWeb;
    }

    /**
     * getter for website of location
     *
     * @return website of location
     */
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
