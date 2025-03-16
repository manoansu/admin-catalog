package pt.amane.domain.exception;

import java.util.List;
import pt.amane.domain.validaion.Error;

public class DomainException extends NoStackTraceException {

  private List<Error> errors;

  private DomainException(final String aMessage, final List<Error> anErrors) {
    super(aMessage);
    this.errors = anErrors;
  }

  public static DomainException with(Error anError) {
    return new DomainException(anError.message(), List.of(anError));
  }

  public static DomainException with(final List<Error> anErrors) {
    return new DomainException("", anErrors);
  }

  public List<Error> getErrors() {
    return errors;
  }
}
