package br.edu.ufcg.ccc.pharma.repository;

import br.edu.ufcg.ccc.pharma.model.Batch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BatchRepository extends CrudRepository<Batch, Long> {
    @Query( value = "SELECT SUM(b.amount) FROM Batch b WHERE product_id = :id AND b.expiration > CURRENT_DATE ")
    public int findTotalAmount(@Param("id") long id);
}
