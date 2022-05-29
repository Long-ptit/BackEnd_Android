package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private int price;
    @ManyToOne
    @JoinColumn(name = "id_bill")//name="tên cột khóa ngoại"
    Bill bill;
    @ManyToOne
    @JoinColumn(name = "id_food")//name="tên cột khóa ngoại"
    Food food;

}
