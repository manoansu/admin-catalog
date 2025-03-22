package pt.amane.application.category.create;

import io.vavr.control.Either;
import pt.amane.application.UseCase;
import pt.amane.domain.validaion.handler.Notification;

/**
 * In java world, you always work for abstration, not for implmentation,
 * because when this class has a new feature , you can only change the implementation not abstration.
 */
public abstract class CreateCategoryUseCase extends
    UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}
