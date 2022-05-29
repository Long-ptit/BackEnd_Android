package com.example.android.repository;

import com.example.android.model.Bill;
import com.example.android.model.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillFoodRepository extends JpaRepository<BillFood, Integer> {
    @Query(value="SELECT * FROM bill_food WHERE id_bill = ?1",nativeQuery = true)
    List<BillFood> getBillFoodByIdBill(int id);
}
