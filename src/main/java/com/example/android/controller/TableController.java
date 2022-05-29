package com.example.android.controller;

import com.example.android.model.TableRestaurant;
import com.example.android.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/table")
public class TableController {

    @Autowired
    TableRepository tableRepository;

    @GetMapping("/getAll")
    public List<TableRestaurant> getListTable() {
        return tableRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public List<TableRestaurant> getTableByIdFloor(@PathVariable("id") int id) {
        return tableRepository.getTableByIdFloor(id);
    }

    @PostMapping("/save")
    public TableRestaurant saveTable(@RequestBody TableRestaurant table) {
        table.setIsActive(1);
        System.out.println(table.getName());
        return tableRepository.save(table);
    }
}