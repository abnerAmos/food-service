package com.food.service.repository;

import com.food.service.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    @Query("SELECT r FROM Kitchen r WHERE (:name is null or r.name LIKE CONCAT('%',:name,'%'))")
    List<Kitchen> findAllByName(String name);

}
