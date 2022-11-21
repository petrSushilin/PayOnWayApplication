package com.petrsushilin.ifmo.payonway.entity;

import com.petrsushilin.ifmo.payonway.entity.nums.ProductType;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    private Long id;
    private String name;
    @Enumerated
    private ProductType type;
    private double cost;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
