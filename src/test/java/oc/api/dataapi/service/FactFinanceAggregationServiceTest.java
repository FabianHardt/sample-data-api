package oc.api.dataapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

import oc.api.dataapi.repo.FactFinaceAggregationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FactFinanceAggregationServiceTest {

  @Mock
  FactFinaceAggregationRepository repo;
  @InjectMocks
  FactFinanceAggregationService factFinanceAggregationService;

  @BeforeEach
  void setUp() {
    doReturn(2L).when(repo).getAmountInRange(eq(20131228L),eq(20131228L));
  }

  @Test
  void getAmountForSingleDay() {
    Long result = factFinanceAggregationService.getAmountForSingleDay("20131228");
    assertThat(result).isEqualTo(2L);
  }

  @Test
  void getAmountForRange() {
    Long result = factFinanceAggregationService.getAmountForRange("20131228", "20131228");
    assertThat(result).isEqualTo(2L);
  }
}