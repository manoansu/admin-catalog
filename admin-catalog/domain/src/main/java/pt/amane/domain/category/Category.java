package pt.amane.domain.category;

import java.time.Instant;
import java.util.Objects;
import pt.amane.domain.AggregateRoot;
import pt.amane.domain.validaion.ValidationHandler;

public class Category extends AggregateRoot<CategoryID> {

  private String name;
  private String description;
  private boolean active;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  private Category(
      final CategoryID categoryID,
      final String name,
      final String description,
      final boolean active,
      final Instant aCreateionDate,
      final Instant aUpdateDate,
      final Instant aDeleteDate) {
    super(categoryID);
    this.name = name;
    this.description = description;
    this.active = active;
    this.createdAt = Objects.requireNonNull(aCreateionDate, "'aCreateionDate' should not be null");
    this.updatedAt = Objects.requireNonNull(aUpdateDate, "'aUpdateDate' should not be null");
    this.deletedAt = aDeleteDate;
  }

  public static Category newCategory(final String aName, final String aDescription, final boolean isActive) {
    final var anId = CategoryID.unique();
    final var now = Instant.now();
    final var aDeletedAt = isActive ? null : now;
    return new Category(anId, aName, aDescription, isActive, now, now, aDeletedAt);
  }

  @Override
  public void validate(final ValidationHandler handler) {
    new CategoryValidator(this, handler).validate();
  }

  public CategoryID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }

  @Override
  public void getValue() {

  }

}
