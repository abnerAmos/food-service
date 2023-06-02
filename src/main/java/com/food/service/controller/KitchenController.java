package com.food.service.controller;

import com.food.service.dto.request.KitchenRequest;
import com.food.service.model.Kitchen;
import com.food.service.model.Restaurant;
import com.food.service.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
        Kitchen kitchen = kitchenService.findById(id);
        return ResponseEntity.ok().body(kitchen);
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> listAll(@RequestParam("name") String name) {
        List<Kitchen> kitchens = kitchenService.listAll(name);
        return ResponseEntity.ok().body(kitchens);
    }

    @PostMapping
    public ResponseEntity<Kitchen> create(@RequestBody @Valid KitchenRequest request) {
        Kitchen kitchen = kitchenService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(kitchen.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.
        return ResponseEntity.created(uri).body(kitchen);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id,
                                          @RequestBody @Valid KitchenRequest request) {
        Kitchen kitchen = kitchenService.update(request, id);
        return ResponseEntity.ok().body(kitchen);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kitchenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
