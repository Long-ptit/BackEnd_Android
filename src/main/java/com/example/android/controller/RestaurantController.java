package com.example.android.controller;

import com.example.android.model.Food;
import com.example.android.model.Restaurant;
import com.example.android.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/getById/{id}")
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        return restaurantRepository.findById(id).get();
    }

    @PostMapping("/save")
    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }


}
