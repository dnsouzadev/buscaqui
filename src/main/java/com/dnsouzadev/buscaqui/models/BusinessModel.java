package com.dnsouzadev.buscaqui.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class BusinessModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    private Double deliveryFee;

    @ManyToOne
    private CategoryModel category;

    public BusinessModel(String name, String address, String phone, Double deliveryFee, CategoryModel category) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.deliveryFee = deliveryFee;
        this.category = category;
    }
}
