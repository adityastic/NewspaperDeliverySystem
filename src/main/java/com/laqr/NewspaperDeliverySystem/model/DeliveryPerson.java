package com.laqr.NewspaperDeliverySystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {

    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route", referencedColumnName = "id")
    @NotNull
    private Route route;

    @Column(name = "phone_no")
    private String phoneNumber;

    public DeliveryPerson(int id, User user, String fullName, Route route, String phoneNumber) {
        this.id = id;
        this.user = user;
        this.fullName = fullName;
        this.route = route;
        this.phoneNumber = phoneNumber;
    }

    public DeliveryPerson() {
    }

    public Integer getId() {
        return id;
    }

    public DeliveryPerson setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DeliveryPerson setUser(User user) {
        this.user = user;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public DeliveryPerson setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public DeliveryPerson setRoute(Route route) {
        this.route = route;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DeliveryPerson setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "id=" + id +
                ", user=" + user +
                ", fullName='" + fullName + '\'' +
                ", route=" + route +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
