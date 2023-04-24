package com.food.service.services.impl;

import com.food.service.dto.request.KitchenRequest;
import com.food.service.model.Kitchen;
import com.food.service.repository.KitchenRepository;
import com.food.service.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public Kitchen create(KitchenRequest request) {

        Kitchen kitchen = new Kitchen();

        try {
            kitchen.setName(request.getName());
            kitchenRepository.save(kitchen);
        } catch (Exception e) {
            throw new ResourceAccessException("NÃO FOI POSSIVEL CRIAR A COZINHA!");
        }
        return kitchen;
    }

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!"));
    }

    @Override
    public List<Kitchen> listAll() {
        List<Kitchen> kitchens = kitchenRepository.findAll();
        if (kitchens.isEmpty())
            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
        return kitchens;
    }

    @Override
    public Kitchen update(KitchenRequest request, Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!"));

        try {
            kitchen.setName(request.getName());
            kitchenRepository.save(kitchen);
        } catch (Exception e) {
            throw new ResourceAccessException("NÃO FOI POSSIVEL ATUALIZAR A COZINHA!");
        }
        return kitchen;
    }

    @Override
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("NÃO FOI POSSIVEL EXCLUIR A COZINHA!");
        }
    }

    public Kitchen findRestaurantByKitchen(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!"));

    }
}
