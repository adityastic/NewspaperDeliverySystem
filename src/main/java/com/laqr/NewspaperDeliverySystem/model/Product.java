package com.laqr.NewspaperDeliverySystem.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private String frequency;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "selling_cost")
    private Double sellingCost;

    @Column(name = "buying_cost")
    private Double buyingCost;

    public Product(String name, ProductType type, String frequency, Integer dayOfWeek, Double sellingCost, Double buyingCost) {
        this.name = name;
        this.type = type;
        this.frequency = frequency;
        this.dayOfWeek = dayOfWeek;
        this.sellingCost = sellingCost;
        this.buyingCost = buyingCost;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public ProductType getType() {
        return type;
    }

    public Product setType(ProductType type) {
        this.type = type;
        return this;
    }

    public String getFrequency() {
        return frequency;
    }

    public Product setFrequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public Product setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Double getSellingCost() {
        return sellingCost;
    }

    public Product setSellingCost(Double sellingCost) {
        this.sellingCost = sellingCost;
        return this;
    }

    public Double getBuyingCost() {
        return buyingCost;
    }

    public Product setBuyingCost(Double buyingCost) {
        this.buyingCost = buyingCost;
        return this;
    }
}
