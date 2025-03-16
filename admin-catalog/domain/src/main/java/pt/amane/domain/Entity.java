package pt.amane.domain;

import java.util.Objects;
import pt.amane.domain.validaion.ValidationHandler;

public abstract class Entity<ID> extends Identifier {

  protected final ID id;

  protected Entity(ID id) {
    Objects.requireNonNull(id, "'id' should not be null");
    this.id = id;
  }

  public abstract void validate(ValidationHandler handler);

  public ID getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entity<?> entity = (Entity<?>) o;
    return Objects.equals(getId(), entity.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }
}
