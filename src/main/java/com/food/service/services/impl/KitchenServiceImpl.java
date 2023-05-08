package com.food.service.services.impl;

import com.food.service.dto.request.KitchenRequest;
import com.food.service.exception.DatabaseException;
import com.food.service.exception.EntityNotCreateOrUpdateException;
import com.food.service.exception.KitchenNotFoundException;
import com.food.service.model.Kitchen;
import com.food.service.repository.KitchenRepository;
import com.food.service.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

    @Override
    public List<Kitchen> listAll(String name) {
        List<Kitchen> kitchens = kitchenRepository.findAllByName(name);
        if (kitchens.isEmpty())
            throw new KitchenNotFoundException();
        return kitchens;
    }

    @Override
    public Kitchen create(KitchenRequest request) {

        Kitchen kitchen = new Kitchen();

        try {
            kitchen.setName(request.getName());
            kitchenRepository.save(kitchen);
        } catch (Exception e) {
            throw new EntityNotCreateOrUpdateException("Cozinha");
        }
        return kitchen;
    }

    @Override
    public Kitchen update(KitchenRequest request, Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));

        try {
            kitchen.setName(request.getName());

        } catch (Exception e) {
            throw new EntityNotCreateOrUpdateException("Cozinha");
        }
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException();
        }
    }

}