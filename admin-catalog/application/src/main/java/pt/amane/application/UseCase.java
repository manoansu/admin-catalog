package pt.amane.application;

public abstract class UseCase<IN, OUT> {

  public abstract OUT execute(IN anIn);

}
