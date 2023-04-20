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
}
