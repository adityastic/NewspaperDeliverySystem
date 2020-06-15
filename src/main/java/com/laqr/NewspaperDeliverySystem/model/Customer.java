package com.laqr.NewspaperDeliverySystem.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "customers")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    private String address;

    @Column(name = "phone_no")
    private Integer phoneNo;

    @Type(type = "json")
    private List<Integer> subscription;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "route", referencedColumnName = "id")
    @NotNull
    private Route route;

    @Type(type = "json")
    private List<String> holiday;

    public Customer(String fullName, String address, Integer phoneNo, List<Integer> subscription, Route route, List<String> holiday) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.subscription = subscription;
        this.route = route;
        this.holiday = holiday;
    }

    public Customer() {
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

    public List<Integer> getSubscription() {
        return subscription;
    }

    public Customer setSubscription(List<Integer> subscription) {
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

    public List<String> getHoliday() {
        return holiday;
    }

    public Customer setHoliday(List<String> holiday) {
        this.holiday = holiday;
        return this;
    }
}
