package br.edu.ufcg.ccc.pharma.category;

import br.edu.ufcg.ccc.pharma.category.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
