package com.codecool.shop.model;

public class Address extends BaseModel{
    private String country;
    private String streetAdress;
    private String city;
    private String state;
    private String zipCode;

    public Address(String country, String streetAdress, String city, String state, String zipCode) {
        this.country = country;
        this.streetAdress = streetAdress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
}
