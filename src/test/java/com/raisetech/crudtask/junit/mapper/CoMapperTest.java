package com.raisetech.crudtask.junit.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.crudtask.infrastructure.entity.Coffee;
import com.raisetech.crudtask.infrastructure.mapper.CoMapper;
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
    @DataSet(value = "datasets/findAll/coffees.yml")
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
    @DataSet(value = "datasets/findById/coffees.yml")
    @Transactional
    public void findById() {
        int id = 1;

        Optional<Coffee> coffee = coMapper.findById(id);
        assertThat(coffee).isNotEmpty();
        assertThat(coffee.get()).isEqualTo(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"));
    }

    @Test
    @DisplayName("新規のコーヒー情報が入力されたときに正常に登録すること")
    @DataSet(value = "datasets/insert/coffees.yml")
    @ExpectedDataSet(value = "datasets/insert/insertCoffees.yml", ignoreCols = "id")
    @Transactional
    public void insert() {
        coMapper.insert(new Coffee(4, LocalDate.of(2023, 4, 4), "ハワイ", "コナ", "浅煎り", "美味しかった"));
    }

    @Test
    @DisplayName("指定されたidのコーヒー情報が更新されること")
    @DataSet(value = "datasets/update/coffees.yml")
    @ExpectedDataSet(value = "datasets/update/updateCoffees.yml", ignoreCols = "id")
    @Transactional
    public void update() {
        coMapper.update(1, new Coffee(1, LocalDate.of(2023, 4, 4), "インドネシア", "マンデリン", "深煎り", "美味しかった"));
    }

    @Test
    @DisplayName("指定されたidのコーヒー情報が削除されること")
    @DataSet(value = "datasets/delete/coffees.yml")
    @ExpectedDataSet(value = "datasets/delete/deleteCoffees.yml")
    @Transactional
    public void delete() {
        coMapper.delete(1);
    }
}
