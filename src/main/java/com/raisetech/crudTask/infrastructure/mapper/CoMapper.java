package com.raisetech.crudTask.infrastructure.mapper;

import com.raisetech.crudTask.infrastructure.entity.Coffee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CoMapper {
    List<Coffee> findAll();

    Optional<Coffee> findById(int id);
}
