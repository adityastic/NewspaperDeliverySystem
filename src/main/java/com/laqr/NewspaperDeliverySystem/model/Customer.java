package com.laqr.NewspaperDeliverySystem.model;

import com.mysql.cj.xdevapi.JsonArray;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String address;

    @Column(name = "phone_no")
    private Integer phoneNo;

    @Column(columnDefinition = "json")
    private JsonArray subscription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route", referencedColumnName = "id")
    private Route route;

    @Column(columnDefinition = "json")
    private JsonArray holiday;

    public Customer(String fullName, String address, Integer phoneNo, JsonArray subscription, Route route, JsonArray holiday) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.subscription = subscription;
        this.route = route;
        this.holiday = holiday;
    }

    public Integer getId() {
        return id;
    }

    public Customer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Customer setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public Customer setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public JsonArray getSubscription() {
        return subscription;
    }

    public Customer setSubscription(JsonArray subscription) {
        this.subscription = subscription;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public Customer setRoute(Route route) {
        this.route = route;
        return this;
    }

    public JsonArray getHoliday() {
        return holiday;
    }

    public Customer setHoliday(JsonArray holiday) {
        this.holiday = holiday;
        return this;
    }
}
