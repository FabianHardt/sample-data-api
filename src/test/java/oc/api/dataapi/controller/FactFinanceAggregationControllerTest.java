package oc.api.dataapi.controller;

import static org.hamcrest.Matchers.equalTo;
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
import oc.api.dataapi.repo.FactFinaceAggregationRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class FactFinanceAggregationControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  FactFinaceAggregationRepository repo;

  @BeforeEach
  void setUp() {
    FactFinance factFinance1 = new FactFinance().financekey(1L).amount(50L).datekey("20131228");
    repo.save(factFinance1);
    FactFinance factFinance2 = new FactFinance().financekey(2L).amount(100L).datekey("20131229");
    repo.save(factFinance2);
  }

  @Test
  void aggdate() throws Exception {
    mockMvc.perform(get("/finance-aggregation")
            .param("fromdate", "20131228")
            .param("todate", "20131229"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.amount", equalTo(150)));
  }

  //@Test
  void aggdateError() throws Exception {
    mockMvc.perform(get("/finance-aggregation")
            .param("fromdate", "20110001"))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }
}