package com.food.service.services.impl;

import com.food.service.dto.request.KitchenRequest;
import com.food.service.exception.DatabaseException;
import com.food.service.exception.EntityNotCreateOrUpdate;
import com.food.service.model.Kitchen;
import com.food.service.repository.KitchenRepository;
import com.food.service.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public Kitchen findById(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!"));
    }

    @Override
    public List<Kitchen> findByName(String name) {
        List<Kitchen> kitchens = kitchenRepository.findByNameContains(name);
        if (kitchens.isEmpty()) {
            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
        }
        return kitchens;
    }

    @Override
    public List<Kitchen> listAll() {
        List<Kitchen> kitchens = kitchenRepository.findAll();
        if (kitchens.isEmpty())
            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
        return kitchens;
    }

    @Override
    public Kitchen create(KitchenRequest request) {

        Kitchen kitchen = new Kitchen();

        try {
            kitchen.setName(request.getName());
            kitchenRepository.save(kitchen);
        } catch (Exception e) {
            throw new EntityNotCreateOrUpdate(e.getMessage() + " :: NÃO FOI POSSIVEL CRIAR OU ATUALIZAR A COZINHA!");
        }
        return kitchen;
    }

    @Override
    public Kitchen update(KitchenRequest request, Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!"));

        try {
            kitchen.setName(request.getName());

        } catch (Exception e) {
            throw new EntityNotCreateOrUpdate(e.getMessage() + " :: NÃO FOI POSSIVEL CRIAR OU ATUALIZAR A COZINHA!");
        }
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException(e.getMessage() + " :: NÃO FOI POSSIVEL EXCLUIR A COZINHA!");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage() + " :: Cozinha possui restaurantes cadastrados!");
        }
    }

}
