package pt.amane.domain.category;

import pt.amane.domain.validaion.Error;
import pt.amane.domain.validaion.ValidationHandler;
import pt.amane.domain.validaion.Validator;

public class CategoryValidator extends Validator {

  private final Category category;

  protected CategoryValidator(final Category category, final ValidationHandler handler) {
    super(handler);
    this.category = category;
  }

  @Override
  public void validate() {
    checkNameConstraint();

  }

  private void checkNameConstraint() {
    final var name = this.category.getName();

    if (name == null) {
      this.validationHandler().append(new Error("'name' should not be null"));
      return;
    }

    if (name.isBlank()) {
      this.validationHandler().append(new Error("'name' should not be empty"));
      return;
    }

    final var lenth = name.trim().length();
    if (lenth > 255 || lenth < 3) {
      this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
      return;
    }
  }

}
