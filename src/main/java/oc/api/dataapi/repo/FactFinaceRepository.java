package oc.api.dataapi.repo;

import oc.api.dataapi.model.FactFinance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactFinaceRepository extends CrudRepository<FactFinance, Long> {
}
