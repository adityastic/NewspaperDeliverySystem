package com.laqr.NewspaperDeliverySystem.model;

import javax.persistence.*;

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
}
