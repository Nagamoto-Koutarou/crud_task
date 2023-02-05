package com.raisetech.crudTask.domain.service;

import com.raisetech.crudTask.infrastructure.entity.Coffee;

import java.util.List;

public interface CoService {
    List<Coffee> findAll();
}
