package com.raisetech.crudTask.application.controller;

import com.raisetech.crudTask.domain.service.CoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/coffees")
@RequiredArgsConstructor
@RestController
public class CoController {

    private final CoService coService;

    @GetMapping
    public List<CoResponse> getCoffees() {
        return coService.findAll().stream().map(CoResponse::new).toList();
    }
}
