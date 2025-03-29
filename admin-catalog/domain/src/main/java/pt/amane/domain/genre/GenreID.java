package pt.amane.domain.genre;

import java.util.Objects;
import java.util.UUID;
import pt.amane.domain.Identifier;

/**
 * Class GenreID generate Id from classes.
 */
public class GenreID extends Identifier {

  private final String value;

  private GenreID(final String value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  /**
   * Generate the unique ID from Genre entity class for each Genre
   * @return
   */
  public static GenreID unique() {
    return GenreID.from(UUID.randomUUID());
  }

  /**
   * Factory method that clone the GenreID contructor, either help to convert database value or test.
   * @param anId
   * @return
   */
  public static GenreID from(final String anId) {
    return new GenreID(anId);
  }

  public static GenreID from(final UUID anId) {
    return new GenreID(anId.toString().toLowerCase());
  }

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenreID that = (GenreID) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
