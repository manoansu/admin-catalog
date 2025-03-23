package pt.amane.application.category.update;

import io.vavr.control.Either;
import pt.amane.application.UseCase;
import pt.amane.domain.validaion.handler.Notification;

/**
 * In java world, you always work for abstration, not for implmentation,
 * because when this class has a new feature , you can only change the implementation not abstration.
 */
public abstract class UpdateCategoryUseCase extends
    UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {

}
