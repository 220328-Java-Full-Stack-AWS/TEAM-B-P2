package com.revature.p2backend.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user", schema = "p2")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_id")
  private Integer id;

  @Column(name="first_name")
    private String firstName;

  @Column(name="last_name")
    private String lastName;

  @Column(unique = true, name="user_name")
  private String userName;

  @Column
  private String email;

  @Column
   private String password;

  @Column(name="phone_number")
  private String phoneNumber;

  @Column(name="credit_card")
  private String creditCard;

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(
            name="user_address",
            schema="p2",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="address_id")}
    )
    private Set<Address> addresses = new HashSet<Address>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Orders> orders = new LinkedList<>();

    public User() {
    }


    public User(String firstName, String lastName, String userName,
                String email, String password, String phoneNumber,
                String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
    }

    public void addOrder(Orders order){
        this.orders.add(order);
}

    public void removeOrder(Orders order){
        this.orders.remove(order);
}


     public Orders getOrderById(Integer id){
        for (Orders order : orders){
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

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
                ", creditCard='" + creditCard + '\'' +
                ", addresses=" + addresses +
                ", orders=" + orders +
                '}';
    }
}
