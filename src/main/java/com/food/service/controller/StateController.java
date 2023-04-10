package com.food.service.controller;

import com.food.service.model.State;
import com.food.service.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping("/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        State state = stateService.findById(id);
        return ResponseEntity.ok().body(state);
    }

    @GetMapping
    public ResponseEntity<List<State>> listAll() {
        List<State> state = stateService.listAll();
        return ResponseEntity.ok().body(state);
    }
}
