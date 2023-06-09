package com.food.service.repository;

import com.food.service.model.Kitchen;
import com.food.service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findByNameContains(String name);

}
