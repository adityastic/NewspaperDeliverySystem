package com.laqr.NewspaperDeliverySystem.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private String frequency;

    @Column (name = "day_of_week")
    private Integer dayOfWeek;

    @Column (name = "selling_cost")
    private Double sellingCost;

    @Column (name = "buying_cost")
    private Double buyingCost;
}
