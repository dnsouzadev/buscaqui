package com.dnsouzadev.buscaqui.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private Double price;

    @ManyToOne
    private BusinessModel business;

    public ProductModel() {
    }

    public ProductModel(String name, String description, Double price, BusinessModel business) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BusinessModel getBusiness() {
        return business;
    }

    public void setBusiness(BusinessModel business) {
        this.business = business;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(business, that.business);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, business);
    }
}