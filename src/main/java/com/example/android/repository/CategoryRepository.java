package com.example.android.repository;

import com.example.android.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<FoodCategory, Integer> {
}
