package com.raisetech.crudTask.junit.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.crudTask.infrastructure.entity.Coffee;
import com.raisetech.crudTask.infrastructure.mapper.CoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoMapperTest {

    @Autowired
    CoMapper coMapper;

    @Test
    @DisplayName("すべてのコーヒー情報が取得できること")
    @DataSet(value = "datasets/coffees.yml")
    @Transactional
    public void findAll() {
        List<Coffee> coffees = coMapper.findAll();
        assertThat(coffees)
                .hasSize(3)
                .contains(
                        new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"),
                        new Coffee(2, LocalDate.of(2023, 2, 2), "モカ", "イエメン", "やや深煎り", "美味しかった"),
                        new Coffee(3, LocalDate.of(2023, 3, 3), "ケニア", "ケニア", "深煎り", "美味しかった")
                );
    }

    @Test
    @DisplayName("存在するコーヒー情報のIDを指定したときに正常にユーザーが返されること")
    @DataSet(value = "datasets/coffees.yml")
    @Transactional
    public void findById() {
        int id = 1;

        Optional<Coffee> coffee = coMapper.findById(id);
        Assertions.assertTrue(coffee.isPresent());
        assertThat(coffee.get()).isEqualTo(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"));
    }
}
