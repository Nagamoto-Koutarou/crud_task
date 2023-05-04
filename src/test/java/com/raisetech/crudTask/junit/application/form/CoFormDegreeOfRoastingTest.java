package com.raisetech.crudTask.junit.application.form;

import com.raisetech.crudTask.application.form.CoForm;
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
public class CoFormDegreeOfRoastingTest {

    @Autowired
    Validator validator;

    private CoForm coForm = new CoForm();
    private BindingResult bindingResult = new BindException(coForm, "coForm");

    @BeforeEach
    public void setUp() {
        bindingResult = new BindException(new CoForm(), "coForm");
    }

    @Test
    public void testNoError() {
        CoForm coForm = new CoForm(LocalDate.of(2023,1,1),"ジャマイカ","ブルーマウンテン","浅煎り","美味しかった");

        validator.validate(coForm, bindingResult);

        assertFalse(bindingResult.hasErrors());
    }


    @Test
    public void testNullError() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン",null,"美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void testEmptyCharacterError() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void testHalf_widthSpaceError() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","   ","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void testTabError() {
        CoForm coForm =new CoForm(LocalDate.of(2023,1,1), "ジャマイカ","ブルーマウンテン","\t","美味しかった");

        validator.validate(coForm, bindingResult);

        assertTrue(bindingResult.hasErrors());
    }
}
