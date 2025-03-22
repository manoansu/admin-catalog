package pt.amane.application.category.retrieve.list;

import pt.amane.application.UseCase;
import pt.amane.domain.pagination.Pagination;
import pt.amane.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
