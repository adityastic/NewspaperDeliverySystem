package com.laqr.NewspaperDeliverySystem.model;


import javax.persistence.*;

@Entity
@Table (name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name = "full_name")
    private String fullName;

    private String address;

    @Column (name = "phone_no")
    private Integer phoneNo;

    private String subscription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route", referencedColumnName = "id")
    private Route route;

    private String holiday;

    public Customer(String fullName, String address, Integer phoneNo, String subscription, Route route, String holiday) {
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

    public String getSubscription() {
        return subscription;
    }

    public Customer setSubscription(String subscription) {
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

    public String getHoliday() {
        return holiday;
    }

    public Customer setHoliday(String holiday) {
        this.holiday = holiday;
        return this;
    }
}
