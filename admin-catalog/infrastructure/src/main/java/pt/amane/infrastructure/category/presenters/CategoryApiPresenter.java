package pt.amane.infrastructure.category.presenters;

import pt.amane.application.category.retrieve.get.CategoryOutput;
import pt.amane.application.category.retrieve.list.CategoryListOutput;
import pt.amane.infrastructure.category.models.CategoryListResponse;
import pt.amane.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
