package pt.amane.application.category.update;

import io.vavr.control.Either;
import pt.amane.application.UseCase;
import pt.amane.domain.validaion.handler.Notification;

public abstract class UpdateCategoryUseCase extends
    UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {

}
