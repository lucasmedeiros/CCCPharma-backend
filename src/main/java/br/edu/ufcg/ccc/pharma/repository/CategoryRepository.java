package br.edu.ufcg.ccc.pharma.repository;

import br.edu.ufcg.ccc.pharma.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
