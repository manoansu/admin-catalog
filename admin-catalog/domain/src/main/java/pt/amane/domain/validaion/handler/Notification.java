package pt.amane.domain.validaion.handler;

import java.util.ArrayList;
import java.util.List;
import pt.amane.domain.exception.DomainException;
import pt.amane.domain.validaion.Error;
import pt.amane.domain.validaion.ValidationHandler;

public class Notification implements ValidationHandler {

  private final List<Error> errors;

  public Notification(final List<Error> errors) {
    this.errors = errors;
  }

  public static Notification create() {
    return new Notification(new ArrayList<>());
  }

  public static Notification create(final Error anError) {
    return new Notification(new ArrayList<>()).append(anError);
  }

  public static Notification create(final Throwable t) {
    return create(new Error(t.getMessage()));
  }

  @Override
  public Notification append(final Error anError) {
    this.errors.add(anError);
    return this;
  }

  @Override
  public ValidationHandler append(final ValidationHandler anHandler) {
    this.errors.addAll(anHandler.getErrors());
    return this;
  }

  @Override
  public ValidationHandler append(final Validation aValidation) {

    try {
      aValidation.validate();;
    }catch (final DomainException ex) {
      this.errors.addAll(ex.getErrors());
    }catch (final Throwable t) {
      this.errors.add(new Error(t.getMessage()));
    }
    return this;
  }

  @Override
  public List<Error> getErrors() {
    return this.errors;
  }

  @Override
  public boolean hasError() {
    return ValidationHandler.super.hasError();
  }
}
