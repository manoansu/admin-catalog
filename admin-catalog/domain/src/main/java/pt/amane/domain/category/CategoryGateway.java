package pt.amane.domain.category;

import java.util.Optional;
import pt.amane.domain.pagination.Pagination;

public interface CategoryGateway {

  Category create(Category aCategory);

  void deleteById(CategoryID anId);

  Optional<Category> findById(CategoryID anId);

  Category update(Category aCategory);

  Pagination<Category> findAll(CategorySearchByQuaery aQuery);

}
