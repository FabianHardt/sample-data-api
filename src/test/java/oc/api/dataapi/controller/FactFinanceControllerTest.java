package oc.api.dataapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import oc.api.dataapi.model.FactFinance;
import oc.api.dataapi.repo.FactFinaceRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class FactFinanceControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  FactFinaceRepository repo;

  @BeforeEach
  void setUp() {
    FactFinance factFinance1 = new FactFinance().financekey(1L).amount(50L).datekey("20131228");
    repo.save(factFinance1);
    FactFinance factFinance2 = new FactFinance().financekey(2L).amount(100L).datekey("20131229");
    repo.save(factFinance2);
  }

  @Test
  void getAllFinanceFacts() throws Exception {
    mockMvc.perform(get("/finance"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$.[0].amount", equalTo(50)))
        .andExpect(jsonPath("$.[1].amount", equalTo(100)));
  }

  @Test
  void getFinanceFactsByKey() throws Exception {
    mockMvc.perform(get("/finance/1"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.amount", equalTo(50)));
  }
}