package com.food.service.services.impl;

import com.food.service.dto.request.ProductRequest;
import com.food.service.dto.response.ProductResponse;
import com.food.service.exception.DatabaseException;
import com.food.service.exception.EntityNotCreateOrUpdate;
import com.food.service.model.Product;
import com.food.service.model.Restaurant;
import com.food.service.repository.ProductRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUM PRODUTO ENCONTRADO!"));
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = productRepository.findByNameContains(name);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("NENHUM PRODUTO ENCONTRADO!");
        }
        return products;
    }

    @Override
    public List<ProductResponse> listAll() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("NENHUM PRODUTO ENCONTRADO!");
        }

        List<ProductResponse> response = new ArrayList<>();
        for(Product value : products) {
            ProductResponse r = new ProductResponse();
            r.setName(value.getName());
            r.setDescription(value.getDescription());
            r.setPrice(value.getPrice());
            r.setActive(value.getActive());
            r.setRestaurant(value.getRestaurant().getName());
            response.add(r);
        }
        return response;
    }

    @Override
    public Product create(ProductRequest request) {

        Product product = new Product();

        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(request.getRestaurantId());
            if(restaurant.isEmpty()){
                throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
            }

            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setActive(true);
            product.setRestaurant(restaurant.get());
            productRepository.save(product);

        } catch (Exception e) {
            throw new EntityNotCreateOrUpdate(e.getMessage() + " :: NÃO FOI POSSIVEL CRIAR OU ATUALIZAR O PRODUTO!");
        }
        return product;
    }

    @Override
    public Product update(ProductRequest request, Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUM PRODUTO ENCONTRADO!"));

        try {
            product.setName(request.getName() == null || request.getName().equals("") ? product.getName() : request.getName());
            product.setDescription(request.getDescription() == null || request.getDescription().equals("") ? product.getDescription() : request.getDescription());
            product.setPrice(request.getPrice() == null ? product.getPrice() : request.getPrice());
            product.setActive(request.getActive() == null ? product.getActive() : request.getActive());
        } catch (Exception e) {
            throw new EntityNotCreateOrUpdate(e.getMessage() + " :: NÃO FOI POSSIVEL CRIAR OU ATUALIZAR O PRODUTO!");
        }
        return product;
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException(e.getMessage() + " :: NÃO FOI POSSIVEL EXCLUIR O PRODUTO!");
        }
    }
}
