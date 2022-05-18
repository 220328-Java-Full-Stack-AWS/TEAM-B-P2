package com.revature.p2backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users" , schema="public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String userName;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="credit_card")
    private String creditCard;//added credit card


//    @ManyToMany(cascade={CascadeType.ALL})
//    @JsonBackReference
//    @JoinTable(
//         name="user_address",
//         schema="public",
//         joinColumns={@JoinColumn(name="user_id")},
//         inverseJoinColumns={@JoinColumn(name="address_id")}
//    )
//    private Set<Address> addresses;
//
//
//    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<Orders> orders = new LinkedList<>();


    public User() {
    }

    public User(String firstName, String lastName, String userName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

//    public Set<Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(Address address) {
//        this.addresses.add(address);
//    }

//    public List<Orders> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Orders order) {
//        this.orders.add(order);
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
//                ", addresses=" + addresses +
//                ", orders=" + orders +
                '}';
    }
}
