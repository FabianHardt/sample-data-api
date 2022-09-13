package oc.api.dataapi.service;

import oc.api.dataapi.repo.FactFinaceAggregationRepository;
import org.springframework.stereotype.Service;

@Service
public class FactFinanceAggregationService {

  private final FactFinaceAggregationRepository factFinaceAggregationRepository;

  public FactFinanceAggregationService(
      FactFinaceAggregationRepository factFinaceAggregationRepository) {
    this.factFinaceAggregationRepository = factFinaceAggregationRepository;
  }

  public Long getAmountForSingleDay(String datefrom){
    return factFinaceAggregationRepository.getAmountInRange(Long.parseLong(datefrom), Long.parseLong(datefrom));
  }

  public Long getAmountForRange(String datefrom, String dateto){
    return factFinaceAggregationRepository.getAmountInRange(Long.parseLong(datefrom), Long.parseLong(dateto));
  }

}
