package com.raisetech.crudTask.junit.service;

import com.raisetech.crudTask.domain.exception.ResourceNotFoundException;
import com.raisetech.crudTask.domain.service.CoServiceImpl;
import com.raisetech.crudTask.infrastructure.entity.Coffee;
import com.raisetech.crudTask.infrastructure.mapper.CoMapper;
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
    @DisplayName("findAllですべてのメッセージが返されること")
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
    @DisplayName("存在するユーザーのIDを指定したときに正常にユーザーが返されること")
    public void findById() {
        doReturn(Optional.of(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"))).when(coMapper).findById(1);

       Coffee actual = coServiceImpl.findById(1);
       assertThat(actual).isEqualTo(new Coffee(1, LocalDate.of(2023, 1, 1), "ブルーマウンテン", "ジャマイカ", "浅煎り", "美味しかった"));
       verify(coMapper, times(1)).findById(1);
    }

    @Test
    @DisplayName("存在しないIDを指定したときにResourceNotFoundExceptionがスローされること")
    public void ThrowResourceNotFoundException() {
        when(coMapper.findById(100)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> coServiceImpl.findById(100));
        verify(coMapper, times(1)).findById(100);
    }
}
