package br.edu.ufcg.ccc.pharma.repository;

import br.edu.ufcg.ccc.pharma.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
