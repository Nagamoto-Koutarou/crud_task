package com.raisetech.crudTask.domain.service;

import com.raisetech.crudTask.infrastructure.entity.Coffee;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CoService {
    List<Coffee> findAll();

    Coffee findById(int id);

    Coffee register(Coffee conversionCoffee, BindingResult result);

    void update(int id, Coffee conversionCoffee, BindingResult result);

    void delete(int id);
}
