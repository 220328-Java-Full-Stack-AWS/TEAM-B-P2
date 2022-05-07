package com.revature.p2backend.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users", schema = "p2")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

  @Column(unique = true)
  private String userName;

@Column
private String password;

@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;

@Column(name="email_address")
private String emailAddress;


@Column(name="phone_number")
private String phoneNumber;

@Column
private String address;

@Column
private String city;

@Column
private String state;



@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;


//So we need an empty constructor and also other construc
    //that has everything but not id

//for the mappers


    public User() {
    }

    public User(String userName, String password, String firstName,
                String lastName, String emailAddress, String phoneNumber,
                String address, String city, String state) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
    }

public void addOrder(Order order){
        this.orders.add(order);
}

public void removeOrder(Order order){
        this.orders.remove(order);
}


public Order getOrderById(Integer id){
        for (Order order : orders){
            if(order.getId().equals(id)){
                return order;
            }
        }

    return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
