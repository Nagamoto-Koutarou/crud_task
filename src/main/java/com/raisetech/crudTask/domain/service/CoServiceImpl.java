package com.raisetech.crudTask.domain.service;

import com.raisetech.crudTask.domain.exception.ResourceNotFoundException;
import com.raisetech.crudTask.infrastructure.entity.Coffee;
import com.raisetech.crudTask.infrastructure.mapper.CoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoServiceImpl implements CoService {

    private final CoMapper coMapper;

    @Override
    public List<Coffee> findAll() {
        return  coMapper.findAll();
    }

    @Override
    public Coffee findById(int id) {
        return this.coMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
    }

    @Override
    public Coffee register(Coffee conversionCoffee) {
        coMapper.insert(conversionCoffee);
        return conversionCoffee;
    }

    @Override
    public void update(int id, Coffee conversionCoffee) {
        this.coMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        coMapper.update(id, conversionCoffee);
    }

    @Override
    public void delete(int id) {
        this.coMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        coMapper.delete(id);
    }
}
