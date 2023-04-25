package com.food.service.controller;

import com.food.service.dto.request.ProductRequest;
import com.food.service.dto.response.ProductResponse;
import com.food.service.model.Product;
import com.food.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/list_like")
    public ResponseEntity<List<Product>> findByRestaurant(@RequestParam("name") String name) {
        List<Product> products = productService.findByName(name);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAll() {
        List<ProductResponse> products = productService.listAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        Product product = productService.create(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();   // Retorna o caminho URI/URL do objeto criado no Header da resposta.
        return ResponseEntity.created(uri).body(product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody ProductRequest request) {
        Product product = productService.update(request, id);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
