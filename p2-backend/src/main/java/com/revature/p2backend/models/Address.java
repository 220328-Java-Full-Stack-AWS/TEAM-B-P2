package com.revature.p2backend.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="address" , schema="public")
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

    public Address( String number, String street, String city, String state, String zipCode,) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }


}
