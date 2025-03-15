package pt.amane.domain.category;

import java.util.Objects;
import java.util.UUID;
import pt.amane.domain.Identifier;

/**
 * Class CategoryID generate Id from classes.
 */
public class CategoryID extends Identifier {

  private final String value;

  private CategoryID(final String value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  /**
   * Generate the unique ID from category entity class for each category
   * @return
   */
  public static CategoryID unique() {
    return CategoryID.from(UUID.randomUUID());
  }

  /**
   * Factory method that clone the categoryID contructor, either help to convert database value or test.
   * @param anId
   * @return
   */
  public static CategoryID from(final String anId) {
    return new CategoryID(anId);
  }

  public static CategoryID from(final UUID anId) {
    return new CategoryID(anId.toString().toLowerCase());
  }

  @Override
  public void getValue() {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryID that = (CategoryID) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
