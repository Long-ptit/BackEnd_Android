package com.example.android.repository;
import com.example.android.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query(value="SELECT * FROM staff WHERE username = ?1 and password=?2 and is_active = 1",nativeQuery = true)
    Staff getStaff(String username, String password);

    @Query(value="SELECT * FROM staff WHERE id_restaurant = ?1 and role='nhanvien' and is_active = 1",nativeQuery = true)
    List<Staff> getStaff(int idRes);

    @Query(value="SELECT * FROM staff WHERE id_restaurant = ?1 and role = 'admin' ",nativeQuery = true)
    Staff getStaffAdmin(int idRes);


}
