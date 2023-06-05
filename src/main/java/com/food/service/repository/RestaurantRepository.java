package com.food.service.repository;

import com.food.service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select count(r) from Restaurant r")
    long countFirstBy();

    // Busca com Like "% %"
    // List<Restaurant> findByNameContains(String name);

    // Se nulo traz todos os resultados, caso contrário da Like no parametro inserido
    @Query("SELECT r FROM Restaurant r WHERE (:name is null or r.name LIKE CONCAT('%',:name,'%'))")
    List<Restaurant> listAllByName(String name);

    @Query("SELECT COUNT(r) FROM Restaurant r WHERE (:name is null or r.name LIKE CONCAT('%',:name,'%'))")
    Long countAllByName(String name);
}