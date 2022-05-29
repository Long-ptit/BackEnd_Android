package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String price;
    private String des;
    @ManyToOne
    @JoinColumn(name = "id_category")//name="tên cột khóa ngoại"
    FoodCategory foodCategory;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")//name="tên cột khóa ngoại"
    Restaurant restaurant;
    private int isActive;
}
