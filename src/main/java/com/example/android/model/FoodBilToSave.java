package com.example.android.model;

import lombok.Data;

import java.util.List;

@Data
public class FoodBilToSave {
    List<Food> data;
    Bill bill;
}
