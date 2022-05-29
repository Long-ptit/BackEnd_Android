package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")//name="tên cột khóa ngoại"
    Restaurant restaurant;
    private int isActive;

}
