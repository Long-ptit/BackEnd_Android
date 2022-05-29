package com.example.android.repository;

import com.example.android.model.Floor;
import com.example.android.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Integer> {

    @Query(value="SELECT * FROM floor WHERE id_restaurant = ?1",nativeQuery = true)
    List<Floor> getFloorByIdRes(int id);
}
