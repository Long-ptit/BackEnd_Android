package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "table_res")
public class TableRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_floor")//name="tên cột khóa ngoại"
    Floor floor;
    private int isActive;
}