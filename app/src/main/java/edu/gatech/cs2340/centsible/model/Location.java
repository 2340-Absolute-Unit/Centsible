package edu.gatech.cs2340.centsible.model;

import java.io.Serializable;

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


    public Location() {

    }

    public void setKey(String inKey) {
        this.key = inKey;
    }

    public String getKey() {
        return this.key;
    }

    public void setName(String inName) {
        this.name = inName;
    }

    public String getName() {
        return this.name;
    }

    public void setLatitude(String inLat) {
        this.latitude = inLat;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLongitude(String inLong) {
        this.longitude = inLong;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setStAddress(String inAdd) {
        this.stAddress = inAdd;
    }

    public String getStAddress() {
        return this.stAddress;
    }

    public void setCity(String inCity) {
        this.city = inCity;
    }

    public String getCity() {
        return this.city;
    }

    public void setState(String inState) {
        this.state = inState;
    }

    public String getState() {
        return this.state;
    }

    public void setZip(String inZip) {
        this.zip = inZip;
    }

    public String getZip() {
        return this.zip;
    }

    public void setType(String inTyp) {
        this.type = inTyp;
    }

    public String getType() {
        return this.type;
    }

    public void setPhone(String inPho) {
        this.phone = inPho;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setWebsite(String inWeb) {
        this.website = inWeb;
    }

    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
