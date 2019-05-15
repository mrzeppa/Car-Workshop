package com.javawebtutor.Models;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "address")

public class Address {
    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "homeNumber")
    private int homeNumber;
    @Column(name = "postCode")
    private int postCode;
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "address",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Users> users;



    public Address() {
    }

    public Address(String street, int homeNumber, int postCode, String city) {
        this.street = street;
        this.homeNumber = homeNumber;
        this.postCode = postCode;
        this.city = city;
    }

    public int getAdressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}