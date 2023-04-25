package com.food.service.services;

import com.food.service.dto.request.ProductRequest;
import com.food.service.dto.response.ProductResponse;
import com.food.service.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    List<Product> findByName(String name);

    List<ProductResponse> listAll();

    Product create(ProductRequest request);

    Product update(ProductRequest request, Long id);

    void delete(Long id);
}
