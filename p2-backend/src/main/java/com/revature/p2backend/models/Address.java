package com.revature.p2backend.models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    Integer id;

    @Column(name = "address_nickname")
    String nickname;

    @Column(name = "street_address")
    String streetAddress;

    @Column(name = "street_name")
    String streetName;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "zip_code")
    String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Address() {
    }

    public Address(String nickname, String streetAddress, String streetName, String city, String state, String zipCode) {
        this.nickname = nickname;
        this.streetAddress = streetAddress;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Address(String nickname, String streetAddress, String streetName, String city, String state, String zipCode, User user) {
        this.nickname = nickname;
        this.streetAddress = streetAddress;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
    }

    public Address(Integer id, String nickname,String streetAddress, String streetName, String city, String state, String zipCode) {
        this.nickname = nickname;
        this.id = id;
        this.streetAddress = streetAddress;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
