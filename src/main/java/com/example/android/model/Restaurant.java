package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String address;
}
