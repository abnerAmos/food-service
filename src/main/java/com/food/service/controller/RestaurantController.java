package com.food.service.controller;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.dto.response.RestaurantResponse;
import com.food.service.model.Restaurant;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.PaymentRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/list_like")
    public ResponseEntity<List<Restaurant>> findByRestaurant(@RequestParam("name") String name) {
        List<Restaurant> restaurants = restaurantService.findByName(name);
        return ResponseEntity.ok().body(restaurants);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> listAll() throws NoSuchFieldException, IllegalAccessException {
        List<RestaurantResponse> restaurants = restaurantService.listAll();
        return ResponseEntity.ok().body(restaurants);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantRequest request) {
        Restaurant restaurant = restaurantService.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(restaurant.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.

        return ResponseEntity.created(uri).body(restaurant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id,
                                             @RequestBody RestaurantRequest request) {
        Restaurant restaurant = restaurantService.update(request, id);
        return ResponseEntity.ok().body(restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
