package pt.amane.infrastructure.configuration.usecase;

import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.amane.application.category.create.CreateCategoryUseCase;
import pt.amane.application.category.create.DefaultCreateCategoryUseCase;
import pt.amane.application.category.delete.DefaultDeleteCategoryUseCase;
import pt.amane.application.category.delete.DeleteCategoryUseCase;
import pt.amane.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import pt.amane.application.category.retrieve.get.GetCategoryByIdUseCase;
import pt.amane.application.category.retrieve.list.DefaultListCategoriesUseCase;
import pt.amane.application.category.retrieve.list.ListCategoriesUseCase;
import pt.amane.application.category.update.DefaultUpdateCategoryUseCase;
import pt.amane.application.category.update.UpdateCategoryUseCase;
import pt.amane.domain.category.CategoryGateway;

@Configuration
public class CategoryUseCaseConfig {

  private final CategoryGateway categoryGateway;


  public CategoryUseCaseConfig(CategoryGateway categoryGateway) {
    this.categoryGateway = Objects.requireNonNull(categoryGateway);
  }

  @Bean
  public CreateCategoryUseCase createCategoryUseCase() {
    return new DefaultCreateCategoryUseCase(categoryGateway);
  }

  @Bean
  public UpdateCategoryUseCase updateCategoryUseCase() {
    return new DefaultUpdateCategoryUseCase(categoryGateway);
  }

  @Bean
  public GetCategoryByIdUseCase getCategoryByIdUseCase() {
    return new DefaultGetCategoryByIdUseCase(categoryGateway);
  }

  @Bean
  public ListCategoriesUseCase listCategoriesUseCase() {
    return new DefaultListCategoriesUseCase(categoryGateway);
  }

  @Bean
  public DeleteCategoryUseCase deleteCategoryUseCase() {
    return new DefaultDeleteCategoryUseCase(categoryGateway);
  }

}
