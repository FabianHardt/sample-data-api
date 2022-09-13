package oc.api.dataapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import oc.api.dataapi.model.FactFinance;
import oc.api.dataapi.repo.FactFinaceRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class FactFinanceAggregationControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  FactFinaceRepository repo;

  @BeforeEach
  void setUp() {
    FactFinance factFinance = new FactFinance().financekey(1L).amount(50L).datekey("20131228");
    repo.save(factFinance);
  }

  @Test
  void aggdate() throws Exception {
    mockMvc.perform(get("/api/v1/finance"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$.[0].amount", equalTo(50)));
  }
}