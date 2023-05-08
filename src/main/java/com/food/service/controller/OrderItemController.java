package com.food.service.controller;

import com.food.service.dto.request.OrderItemRequest;
import com.food.service.model.OrderItem;
import com.food.service.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> findById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.findById(id);
        return ResponseEntity.ok().body(orderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> listAll() {
        List<OrderItem> orderItems = orderItemService.listAll();
        return ResponseEntity.ok().body(orderItems);
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemRequest request) {
        OrderItem orderItem = orderItemService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderItem.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.
        return ResponseEntity.created(uri).body(orderItem);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id,
                                          @RequestBody OrderItemRequest request) {
        OrderItem orderItem = orderItemService.update(request, id);
        return ResponseEntity.ok().body(orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
