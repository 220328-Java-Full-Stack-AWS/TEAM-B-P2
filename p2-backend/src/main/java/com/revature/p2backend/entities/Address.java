package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="address" , schema="public")
@JsonIgnoreProperties
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer addressId;

    @Column
    private String number;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String state;

    @Column(name="zip_code")
    private String zipCode;

    @OneToMany(mappedBy="address",fetch = FetchType.LAZY)
    private List<Orders> orders;

    public Address() {
    }

    public Address(String number, String street, String city, String state, String zipCode) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public List<Orders> getOrders() {
        return orders;
    }


    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", number='" + number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && number.equals(address.number) && street.equals(address.street) && city.equals(address.city) && state.equals(address.state) && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, number, street, city, state, zipCode);
    }
}
