package com.example.democrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data //genereaza automat getteri, setteri, toString, hashcode
@AllArgsConstructor //genereaza constructorul cu toti parametrii
@NoArgsConstructor //genereaza constructorul fara parametrii
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private Set<Order> orderList;
}
