package com.revature.p2backend.entities;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "p2myversion")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@Column
  private String userName;
@Column
private String password;

@Column(name="first_name")
private String firstName;

@Column(name="last_name")
private String lastName;

//So we need an empty constructor and also other construc
    //that has everything but not id

//for the mappers
    public User() {
    }

    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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




}
