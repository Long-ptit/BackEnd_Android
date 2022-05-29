package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Data
@Table
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String address;
    private String date;
    private int idManage;
    private int isActive;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")//name="tên cột khóa ngoại"
    Restaurant restaurant;
}
