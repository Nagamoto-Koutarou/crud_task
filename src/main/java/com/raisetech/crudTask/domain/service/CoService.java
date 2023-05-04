package com.raisetech.crudTask.domain.service;

import com.raisetech.crudTask.infrastructure.entity.Coffee;

import java.util.List;

public interface CoService {
    List<Coffee> findAll();

    Coffee findById(int id);

    Coffee register(Coffee conversionCoffee);

    void update(int id, Coffee conversionCoffee);

    void delete(int id);
}
