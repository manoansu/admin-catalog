package pt.amane.domain.exception;

public class NoStackTraceException extends RuntimeException {

  public NoStackTraceException(final String message) {
    this(message, null);
  }

  /**
   * Constructs a new runtime exception with the specified detail message, cause, suppression enabled or disabled,
   * and writable stack trace enabled or disabled.
   * @param message
   * @param cause
   */
  public NoStackTraceException(final String message, final Throwable cause) {
    super(message, cause, true, false);
  }
}
