package pt.amane.application.category.create;

import pt.amane.domain.category.Category;
import pt.amane.domain.category.CategoryID;

public record CreateCategoryOutput(
    CategoryID id
) {

  //Fatctory method
  public static CreateCategoryOutput from(final CategoryID anId) {
    return new CreateCategoryOutput(anId);
  }

  public static CreateCategoryOutput from(final Category aCategory) {
    return new CreateCategoryOutput(aCategory.getId());
  }

}
