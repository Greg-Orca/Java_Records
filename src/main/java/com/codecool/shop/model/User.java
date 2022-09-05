package com.codecool.shop.model;

public class User extends BaseModel{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Address adress;

    public User(String firstName, String lastName, String phoneNumber, String email, Address adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAdress() {
        return adress;
    }
}
