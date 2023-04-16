package com.raisetech.crudTask.junit.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("すべてのユーザーが取得できること")
    @DataSet(value = "datasets/coffees.yml")
    @Transactional
    void findAll() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/coffees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                [
                   {
                      "id": 1,
                      "created_date": "2023-01-01",
                      "countryOfOrigin": "ブルーマウンテン",
                      "productName": "ジャマイカ",
                      "degreeOfRoasting": "浅煎り",
                      "thoughts": "美味しかった"
                   },
                   {
                      "id": 2,
                      "created_date": "2023-02-02",
                      "countryOfOrigin": "モカ",
                      "productName": "イエメン",
                      "degreeOfRoasting": "やや浅煎り",
                      "thoughts": "美味しかった"
                   },
                   {
                      "id": 3,
                      "created_date": "2023-03-03",
                      "countryOfOrigin": "ケニア",
                      "productName": "ケニア",
                      "degreeOfRoasting": "深煎り",
                      "thoughts": "美味しかった"
                   },
                ]
                """, response, JSONCompareMode.STRICT);
    }
}
