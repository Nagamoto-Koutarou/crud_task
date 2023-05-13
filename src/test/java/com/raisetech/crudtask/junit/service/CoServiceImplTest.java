package com.raisetech.crudtask.junit.service;

import com.raisetech.crudtask.domain.exception.ResourceNotFoundException;
import com.raisetech.crudtask.domain.service.CoServiceImpl;
import com.raisetech.crudtask.infrastructure.entity.Coffee;
import com.raisetech.crudtask.infrastructure.mapper.CoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoServiceImplTest {

    @InjectMocks
    CoServiceImpl coServiceImpl;

    @Mock
    CoMapper coMapper;

    @Test
    @DisplayName("findAllですべてのコーヒー情報が返されること")
    public void findAll() {
        doReturn(List.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"),
                (new Coffee(2, LocalDate.of(2023, 2, 2), "モカ", "イエメン", "やや深煎り", "美味しかった")),
                (new Coffee(3, LocalDate.of(2023, 3, 3), "ケニア", "ケニア", "深煎り", "美味しかった")))).when(coMapper).findAll();

        List<Coffee> actual = coServiceImpl.findAll();
        assertThat(actual).isEqualTo(List.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"),
                (new Coffee(2, LocalDate.of(2023, 2, 2), "モカ", "イエメン", "やや深煎り", "美味しかった")),
                (new Coffee(3, LocalDate.of(2023, 3, 3), "ケニア", "ケニア", "深煎り", "美味しかった"))));
        verify(coMapper, times(1)).findAll();
    }

    @Test
    @DisplayName("存在するコーヒー情報のIDを指定したときに正常にユーザーが返されること")
    public void findById() {
        doReturn(Optional.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"))).when(coMapper).findById(1);

       Coffee actual = coServiceImpl.findById(1);
       assertThat(actual).isEqualTo(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"));
       verify(coMapper, times(1)).findById(1);
    }

    @Test
    @DisplayName("ID検索時に存在しないコーヒー情報のIDを指定したときにResourceNotFoundExceptionがスローされること")
    public void throwResourceNotFoundExceptionCaseOfFindById() {
        doReturn(Optional.empty()).when(coMapper).findById(100);
        assertThrows(ResourceNotFoundException.class, () -> coServiceImpl.findById(100));
    }

    @Test
    @DisplayName("新規のコーヒー情報が入力されたときに正常に登録されること")
    public void register() {
        doNothing().when(coMapper).insert(new Coffee(4, LocalDate.of(2023, 4, 4), "ハワイ", "コナ", "浅煎り", "美味しかった"));

        Coffee actual = coServiceImpl.register(new Coffee(4, LocalDate.of(2023, 4, 4), "ハワイ", "コナ", "浅煎り", "美味しかった"));
        assertThat(actual).isEqualTo(new Coffee(4, LocalDate.of(2023, 4, 4), "ハワイ", "コナ", "浅煎り", "美味しかった"));
        verify(coMapper, times(1)).insert(new Coffee(4, LocalDate.of(2023, 4, 4), "ハワイ", "コナ", "浅煎り", "美味しかった"));
    }

    @Test
    @DisplayName("存在するidに対応するコーヒー情報が更新されること")
    public void update() {

        Coffee coffee = new Coffee(1,LocalDate.of(2023, 5, 5), "インド", "モンスーン", "中煎り", "美味しかった");
        doReturn(Optional.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"))).when(coMapper).findById(1);
        doNothing().when(coMapper).update(1, new Coffee(1, LocalDate.of(2023, 5, 5), "インド", "モンスーン", "中煎り", "美味しかった"));
        coServiceImpl.update(1, coffee);
        verify(coMapper, times(1)).update(1, new Coffee(1, LocalDate.of(2023, 5, 5), "インド", "モンスーン", "中煎り", "美味しかった"));
    }

    @Test
    @DisplayName("更新時に存在しないコーヒー情報のIDを指定したときにResourceNotFoundExceptionがスローされること")
    public void throwResourceNotFoundExceptionCaseOfUpdate() {
        doReturn(Optional.empty()).when(coMapper).findById(100);
        assertThrows(ResourceNotFoundException.class, () -> coServiceImpl.update(100,new Coffee(4, LocalDate.of(2023, 4, 4), "国", "名前", "煎り度合い", "感想")));
    }

    @Test
    @DisplayName("存在するidに対応するコーヒー情報が削除されること")
    public void delete() {
        doReturn(Optional.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"))).when(coMapper).findById(1);

        doNothing().when(coMapper).delete(1);
        coServiceImpl.delete(1);
        verify(coMapper, times(1)).findById(1);
        verify(coMapper, times(1)).delete(1);
    }

    @Test
    @DisplayName("削除時に存在しないコーヒー情報のIDを指定したときにResourceNotFoundExceptionがスローされること")
    public void throwResourceNotFoundExceptionCaseOfDelete() {
        doReturn(Optional.empty()).when(coMapper).findById(100);
        assertThrows(ResourceNotFoundException.class, () -> coServiceImpl.delete(100));
    }
}
