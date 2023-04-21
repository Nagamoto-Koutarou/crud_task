package com.raisetech.crudTask.junit.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
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
    @DisplayName("すべてのコーヒー情報が取得できること")
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
                      "degreeOfRoasting": "やや深煎り",
                      "thoughts": "美味しかった"
                   },
                   {
                      "id": 3,
                      "created_date": "2023-03-03",
                      "countryOfOrigin": "ケニア",
                      "productName": "ケニア",
                      "degreeOfRoasting": "深煎り",
                      "thoughts": "美味しかった"
                   }
                ]
                """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DisplayName("存在するコーヒー情報のIDを指定したときに正常にユーザーが返されること")
    @DataSet(value = "datasets/coffees.yml")
    @Transactional
    void findById() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/coffees/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                   {
                      "id": 1,
                      "created_date": "2023-01-01",
                      "countryOfOrigin": "ブルーマウンテン",
                      "productName": "ジャマイカ",
                      "degreeOfRoasting": "浅煎り",
                      "thoughts": "美味しかった"
                   }
                """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DisplayName("存在しないコーヒー情報のIDを指定したときに404のステータスコードが返され、レスポンスボディがNot Foundを表すJSONであること")
    @DataSet
    @Transactional
    void findByIdException() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/coffees/{id}", 100))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                 {
                    "error": "Not Found",
                    "status": "404",
                    "path": "/coffees/100",
                    "message": "resource not found",
                    "timestamp": "2023-04-20T10:50:29.604145900+09:00[Asia/Tokyo]"
                 }
                """, response, new CustomComparator(JSONCompareMode.LENIENT,new Customization("timestamp", (o1, o2) -> true)));
    }
}
