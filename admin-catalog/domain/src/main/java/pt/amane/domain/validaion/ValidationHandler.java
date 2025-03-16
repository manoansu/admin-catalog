package pt.amane.domain.validaion;

import java.util.List;

public interface ValidationHandler {

  ValidationHandler append(Error anError);

  ValidationHandler append(ValidationHandler anHandler);

  ValidationHandler append(Validation aValidation);

  List<Error> getErrors();

  /**
   * verify if list hass error or not.
   * @return
   */
  default boolean hasError() {
    return getErrors() != null && !getErrors().isEmpty();
  }

  /**
   * Posibilidade de lançar um metodo como lambda functiom que lança exception.
   */
  public interface Validation {
    void  validate();
  }

}
