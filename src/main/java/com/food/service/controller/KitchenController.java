package com.food.service.controller;

import com.food.service.model.Kitchen;
import com.food.service.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Kitchen>> findById(@PathVariable Long id) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(id);
        return ResponseEntity.ok().body(kitchen);
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> listAll() {
        List<Kitchen> listAll = kitchenRepository.findAll();
        return ResponseEntity.ok().body(listAll);
    }

    @PostMapping
    public ResponseEntity<Kitchen> create(@RequestBody Kitchen typeKitchen) {

        Kitchen kitchen = kitchenRepository.save(typeKitchen);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(kitchen.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.
        return ResponseEntity.created(uri).body(kitchen);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id,
                                          @RequestBody Kitchen typeKitchen) {
        Kitchen kitchen = kitchenRepository.findById(id).get();
        kitchen.setName(typeKitchen.getName());
        kitchenRepository.save(kitchen);
        return ResponseEntity.ok().body(kitchen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kitchenRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
