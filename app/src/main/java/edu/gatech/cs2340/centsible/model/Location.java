package edu.gatech.cs2340.centsible.model;

public class Location {
    private String key;
    private String name;
    private double latitude;
    private double longitude;
    private String stAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;


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

    public void setLatitude(double inLat) {
        this.latitude = inLat;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double inLong) {
        this.longitude = inLong;
    }

    public double getLongitude() {
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
}
