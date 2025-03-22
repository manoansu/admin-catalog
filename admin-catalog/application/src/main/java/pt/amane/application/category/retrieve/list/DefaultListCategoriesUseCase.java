package pt.amane.application.category.retrieve.list;

import java.util.Objects;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.pagination.Pagination;
import pt.amane.domain.pagination.SearchQuery;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Override
  public Pagination<CategoryListOutput> execute(final SearchQuery anIn) {
    return this.categoryGateway.findAll(anIn)
        .map(CategoryListOutput::from);
  }
}
