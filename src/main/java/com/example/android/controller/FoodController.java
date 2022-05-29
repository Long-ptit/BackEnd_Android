package com.example.android.controller;

import com.example.android.model.Food;
import com.example.android.repository.CategoryRepository;
import com.example.android.repository.FoodRepository;
import com.example.android.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/v1/food/")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("getAllFood")
    public List<Food> getListFood() {
        return foodRepository.getAllActive();
    }

    @GetMapping("getFoodByIdRes/{id}")
    public List<Food> getFoodByIdRes(@PathVariable("id") int id) {
        return foodRepository.getListFoodByIdRes(id);
    }

    @PostMapping("saveFood")
    public Food saveFood(
            @RequestParam("img") MultipartFile file,
            @RequestParam("id_res") String id_res,
            @RequestParam("id_cate") String id_cate,
            @RequestParam("name_food") String name_food,
            @RequestParam("price_food") String price_food,
            @RequestParam("description_food") String description_food
                         ) throws IOException {
        Food food = new Food();
        food.setName(name_food);
        food.setFoodCategory(categoryRepository.findById(Integer.parseInt(id_cate)).get());
        food.setRestaurant(restaurantRepository.findById(Integer.parseInt(id_res)).get());
        food.setPrice(price_food);
        food.setDes(description_food);
        food.setIsActive(1);

        Food foodSave = foodRepository.save(food);

        String folder = "photos/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(foodSave.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return foodSave;
    }

    @ResponseBody
    @GetMapping("getImage/{photo}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo) throws IOException {
            Path fileName = Paths.get("photos", photo);
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
            return ResponseEntity.ok()
                    .contentLength(buffer.length)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(byteArrayResource);
    }

    @GetMapping("deleteFood/{id}")
    public Food getListFood(@PathVariable("id") int id) {
        Food food = foodRepository.findById(id).get();
        food.setIsActive(0);
       return foodRepository.save(food);
    }

}
