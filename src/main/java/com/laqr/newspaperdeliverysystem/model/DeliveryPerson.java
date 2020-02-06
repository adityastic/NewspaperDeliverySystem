package com.laqr.newspaperdeliverysystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Column;

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
    private Long phoneNumber;

    public DeliveryPerson(User user, String fullName, Route route, Long phoneNumber) {
        this.user = user;
        this.fullName = fullName;
        this.route = route;
        this.phoneNumber = phoneNumber;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public DeliveryPerson setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
