package com.example.intellistartproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "prouduct_list")
@Data
public class ProductListForUser {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
