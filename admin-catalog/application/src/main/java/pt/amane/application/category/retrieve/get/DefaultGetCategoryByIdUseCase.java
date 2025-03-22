package pt.amane.application.category.retrieve.get;

import java.util.Objects;
import java.util.function.Supplier;
import pt.amane.domain.category.Category;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.category.CategoryID;
import pt.amane.domain.exception.DomainException;
import pt.amane.domain.exception.NotFoundException;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

  private final CategoryGateway categoryGateway;

  public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }


  @Override
  public CategoryOutput execute(String anIn) {
    final var aCategoryID = CategoryID.from(anIn);
    return categoryGateway.findById(aCategoryID)
        .map(CategoryOutput::from)
        .orElseThrow(notFound(aCategoryID));

  }

  private static Supplier<DomainException> notFound(final CategoryID anId) {
    return () -> NotFoundException.with(Category.class, anId);
  }
}
