package com.raisetech.crudtask.junit.application.form;

import com.raisetech.crudtask.application.form.CoForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CoFormTest {

    @Autowired
    Validator validator;

    private CoForm coForm = new CoForm();
    private BindingResult bindingResult = new BindException(coForm, "coForm");

    @BeforeEach
    public void setUp() {
        bindingResult = new BindException(new CoForm(), "coForm");
    }

    @Test
    public void 正常エラーなし() {
        CoForm coForm = new CoForm(LocalDate.of(2023,1,1),"ジャマイカ","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertFalse(bindingResult.hasErrors());
    }

    @Test
    public void createdDateがnullでエラーが検出されること() {
        CoForm coForm =new CoForm(null, "ジャマイカ","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void countryOfOriginがnullでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), null,"ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void countryOfOriginが空文字でエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void countryOfOriginが半角スペースでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "   ","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void countryOfOriginがタブキーでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "\t","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void productNameがnullでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ",null,"浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void productNameが空文字でエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void productNameが半角スペースでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","   ","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void productNameがタブキーでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","\t","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void degreeOfRoastingがnullでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン",null,"美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void degreeOfRoastingが空文字でエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void degreeOfRoastingが半角スペースでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","   ","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void degreeOfRoastingがタブキーでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","\t","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void thoughtsがnullでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブル－マウンテン","浅煎り",null);

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void thoughtsが空文字でエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","浅煎り","");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void thoughtsが半角スペースでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","浅煎り","   ");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void thoughtsがタブキーでエラーが検出されること() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","浅煎り","\t");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }
}
