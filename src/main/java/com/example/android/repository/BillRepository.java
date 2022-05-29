package com.example.android.repository;

import com.example.android.model.Bill;
import com.example.android.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value="SELECT * FROM bill WHERE id_restaurant = ?3 and date_new between ?1 and ?2",nativeQuery = true)
    List<Bill> getDateBetween(Date date1, Date date2, int idRes);

    @Query(value="SELECT * FROM bill WHERE date_new like %?1% and id_restaurant = ?2",nativeQuery = true)
    List<Bill> getDateLike(String key, int idRes);

    @Query(value="SELECT * FROM bill WHERE date_new like %?1% and id_restaurant = ?2",nativeQuery = true)
    Integer getDateLikeSum(String key, int idRes);

    @Query(value="SELECT * FROM bill WHERE id_restaurant = ?1",nativeQuery = true)
    List<Bill> getListBillByIdRes(int idRes);

    @Query(value="SELECT * FROM bill WHERE id_restaurant = ?1 ORDER BY total_price;",nativeQuery = true)
    List<Bill> getListSortAscend(int idRes);

    @Query(value="SELECT * FROM bill WHERE id_restaurant = ?1 ORDER BY total_price DESC;",nativeQuery = true)
    List<Bill> getListSortDescend(int idRes);

    @Query(value="SELECT * FROM bill WHERE  id_restaurant = ?3 and date_new between ?1 and ?2  ORDER BY total_price;",nativeQuery = true)
    List<Bill> getDateBetweenAscend(Date date1, Date date2, int idRes);

    @Query(value="SELECT * FROM bill WHERE id_restaurant = ?3 and date_new between ?1 and ?2  ORDER BY total_price DESC;",nativeQuery = true)
    List<Bill> getDateBetweenDescend(Date date1, Date date2, int idRes);
}
