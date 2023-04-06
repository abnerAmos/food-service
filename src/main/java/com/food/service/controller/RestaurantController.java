package com.food.service.controller;

import com.food.service.model.Kitchen;
import com.food.service.model.Restaurant;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.request.RestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurant>> findById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> listAll() {
        List<Restaurant> listAll = restaurantRepository.findAll();
        return ResponseEntity.ok().body(listAll);
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        var kitchen = kitchenRepository.findById(request.getKitchenId());

        restaurant.setName(request.getName());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setKitchen(kitchen.get());

        restaurantRepository.save(restaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(restaurant.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.
        return ResponseEntity.created(uri).body(restaurant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id,
                                             @RequestBody RestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Kitchen kitchen = kitchenRepository.findById(request.getKitchenId())
                .orElseThrow(EntityNotFoundException::new);

        restaurant.setName(request.getName());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setKitchen(kitchen);

        restaurantRepository.save(restaurant);
        return ResponseEntity.ok().body(restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
