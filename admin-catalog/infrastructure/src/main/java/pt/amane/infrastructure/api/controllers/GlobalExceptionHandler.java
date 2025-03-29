package pt.amane.infrastructure.api.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.amane.domain.exception.DomainException;
import pt.amane.domain.exception.NotFoundException;
import pt.amane.domain.validaion.Error;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(final NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
  }

  @ExceptionHandler(value = DomainException.class)
  public ResponseEntity<?> handleDomainException(final DomainException ex) {
    return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
  }

  record ApiError(String message, List<Error> errors) {
    static ApiError from(final DomainException ex) {
      return new ApiError(ex.getMessage(), ex.getErrors());
    }
  }
}

