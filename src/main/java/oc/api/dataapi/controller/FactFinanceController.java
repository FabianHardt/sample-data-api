package oc.api.dataapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oc.api.dataapi.endpoint.FinanceApi;
import oc.api.dataapi.model.FactFinance;
import oc.api.dataapi.repo.FactFinaceRepository;

@RestController
@RequestMapping("/")
public class FactFinanceController implements FinanceApi {

  @Autowired
  protected FactFinaceRepository factFinaceRepository;

  @Override
  public ResponseEntity<List<FactFinance>> getAllFinanceFacts() {
    List<FactFinance> factFinanceList = (List<FactFinance>) factFinaceRepository.findAll();
    return new ResponseEntity<List<FactFinance>>(factFinanceList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FactFinance> getFinanceFactsByKey( @PathVariable("financekey") Long financekey) {
    return ResponseEntity.of(factFinaceRepository.findById(financekey));
  }

}
