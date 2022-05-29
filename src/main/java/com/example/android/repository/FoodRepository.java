package com.example.android.repository;

import com.example.android.model.Food;
import com.example.android.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query(value="SELECT * FROM food WHERE id_restaurant=?1 and is_active = 1",nativeQuery = true)
    List<Food> getListFoodByIdRes(int idRes);

    @Query(value="SELECT * FROM food WHERE is_active=1",nativeQuery = true)
    List<Food> getAllActive();

}
