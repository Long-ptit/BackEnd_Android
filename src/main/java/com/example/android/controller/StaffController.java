package com.example.android.controller;

import com.example.android.model.Restaurant;
import com.example.android.model.Staff;
import com.example.android.repository.RestaurantRepository;
import com.example.android.repository.StaffRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff/")
public class StaffController {

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping("/getUser/{id}")
    public Staff getUser(@PathVariable("id") int id) {
        return staffRepository.findById(id).get();

    }

    @PostMapping("/loginStaff")
    public Staff getLoginStaff(@RequestBody Staff staff) {
        System.out.println("username"  + staff.getUsername());
        System.out.println("password" + staff.getPassword());
        Staff staffResult = staffRepository.getStaff(
                staff.getUsername(),
                staff.getPassword());
        if (staffResult != null) {
            return staffResult;
        }
        System.out.println("bi null");
        return new Staff();
    }

    @GetMapping("/getByIdRes/{id}")
    public List<Staff> getLoginStaff(@PathVariable("id") int idRes) {
        List<Staff> staffResult = staffRepository.getStaff(idRes);
        return staffResult;
    }

    @PostMapping("/saveStaff")
    public Staff saveStaff(@RequestBody Staff staff) {
        staff.setIsActive(1);
        staff.setIdManage(staffRepository.getStaffAdmin(staff.getRestaurant().getId()).getId());
        return staffRepository.save(staff);
    }

    @PostMapping("/saveStaffAdmin")
    public Staff saveStaffAdmin(@RequestBody Staff staff) {
        staff.setIsActive(1);
        Restaurant restaurant = restaurantRepository.findById(staff.getRestaurant().getId()).get();
        restaurant.setName(staff.getRestaurant().getName());
        restaurant.setAddress(staff.getAddress());
        restaurantRepository.save(restaurant);
        return staffRepository.save(staff);
    }

    @PostMapping("/signup")
    public Staff signUp(@RequestBody Staff staff) {
        staff.setIsActive(1);
        Restaurant restaurant = restaurantRepository.save(staff.getRestaurant());
        staff.setRestaurant(restaurant);

        return staffRepository.save(staff);
    }

    @GetMapping("/delete/{id}")
    public Staff delete(@PathVariable("id") int id) {
        Staff staff = staffRepository.findById(id).get();
        staff.setIsActive(0);
        return staffRepository.save(staff);
    }

}
