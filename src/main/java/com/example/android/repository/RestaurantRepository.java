package com.example.android.repository;

import com.example.android.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query(value="SELECT * FROM restaurant WHERE username = ?1 and password=?2",nativeQuery = true)
    Restaurant getRestaurant(String username, String password);
}
