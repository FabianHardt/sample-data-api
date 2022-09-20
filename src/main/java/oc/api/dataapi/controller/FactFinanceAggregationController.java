package oc.api.dataapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oc.api.dataapi.endpoint.FinanceAggregationApi;
import oc.api.dataapi.model.FinaceAggregate;
import oc.api.dataapi.service.FactFinanceAggregationService;

@RestController
@RequestMapping("/")
public class FactFinanceAggregationController implements FinanceAggregationApi {

  private final FactFinanceAggregationService factFinanceAggregationService;

  public FactFinanceAggregationController(
      FactFinanceAggregationService factFinanceAggregationService) {
    this.factFinanceAggregationService = factFinanceAggregationService;
  }

  @Override
  public ResponseEntity<FinaceAggregate> aggdate(String fromdate, String todate) {
    Long resultAmount = 0L;
    if (todate == null || todate.isEmpty()) {
      resultAmount = factFinanceAggregationService.getAmountForSingleDay(fromdate);
    } else {
      resultAmount = factFinanceAggregationService.getAmountForRange(fromdate, todate);
    }
    FinaceAggregate finaceAggregate = new FinaceAggregate().amount(resultAmount);
    return ResponseEntity.ok(finaceAggregate);
  }
}
