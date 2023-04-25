package com.food.service.repository;

import com.food.service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Busca com Like "% %"
    List<Restaurant> findByNameContains(String name);

//    @Query(value = "select name, delivery_fee, active, open_up, date_registrer, date_update from tb_restaurant", nativeQuery = true)
//    List<Restaurant> listAll();
}