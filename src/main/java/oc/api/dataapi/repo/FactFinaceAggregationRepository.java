package oc.api.dataapi.repo;

import java.util.List;
import oc.api.dataapi.model.FactFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactFinaceAggregationRepository extends CrudRepository<FactFinance, Long> {

  @Query(value = "SELECT SUM(amount) FROM factfinance u WHERE u.datekey between ?1 and ?2", nativeQuery = true)
  public Long getAmountInRange(long datefrom, long dateto);

}
