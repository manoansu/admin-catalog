package pt.amane.domain;

public abstract class Entity<ID> extends Identifier {

  private final ID id;

  protected Entity(ID id) {
    this.id = id;
  }
}
