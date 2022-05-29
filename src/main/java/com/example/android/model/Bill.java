package com.example.android.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")//name="tên cột khóa ngoại"
    Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "id_staff")//name="tên cột khóa ngoại"
    Staff staff;
    String date;
    String totalPrice;
    String idBan;
    int quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    Date dateNew;

}
