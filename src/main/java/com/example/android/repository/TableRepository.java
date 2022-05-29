package com.example.android.repository;

import com.example.android.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableRepository extends JpaRepository<TableRestaurant, Integer> {

    @Query(value="SELECT * FROM table_res WHERE id_floor = ?1",nativeQuery = true)
    List<TableRestaurant> getTableByIdFloor(int id);

}
