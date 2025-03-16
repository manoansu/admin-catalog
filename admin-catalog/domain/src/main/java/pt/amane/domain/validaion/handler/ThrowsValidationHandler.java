package pt.amane.domain.validaion.handler;

import java.util.List;
import pt.amane.domain.exception.DomainException;
import pt.amane.domain.validaion.Error;
import pt.amane.domain.validaion.ValidationHandler;

public class ThrowsValidationHandler implements ValidationHandler {

  @Override
  public ValidationHandler append(final Error anError) {
    throw DomainException.with(anError);
  }

  @Override
  public ValidationHandler append(final ValidationHandler anHandler) {
    throw DomainException.with(anHandler.getErrors());
  }

  @Override
  public ValidationHandler append(final Validation aValidation) {
    try {
      aValidation.validate();
    }catch (Exception e) {
      DomainException.with(new Error(e.getMessage()));
    }

    return this;
  }

  @Override
  public List<Error> getErrors() {
    return List.of();
  }
}
