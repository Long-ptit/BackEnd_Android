package com.example.android.controller;

import com.example.android.model.Floor;
import com.example.android.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/floor")
public class FloorController {

    @Autowired
    FloorRepository floorRepository;

    @GetMapping("/getAll")
    public List<Floor> getListFloor() {
        return floorRepository.findAll();
    }

    @GetMapping("/getByIdRes/{id}")
    public List<Floor> getListFloor(@PathVariable("id") int id) {
        return floorRepository.getFloorByIdRes(id);
    }


    @PostMapping("/save")
    public Floor saveFloor(@RequestBody Floor floor) {
        System.out.println(floor.getRestaurant().getId() + "id la");
        System.out.println(floor.getName() + "name la");
        floorRepository.save(floor);
        return new Floor();
    }

}
