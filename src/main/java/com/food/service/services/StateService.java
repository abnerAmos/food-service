package com.food.service.services;

import com.food.service.model.State;

import java.util.List;

public interface StateService {

//    State create(StateRequest request);

    State findById(Long id);

    List<State> listAll();

//    State update(StateRequest request, Long id);

//    void delete(Long id);
}
