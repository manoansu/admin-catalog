package pt.amane.application.genre.retrieve.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import pt.amane.IntegrationTest;
import pt.amane.domain.category.Category;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.category.CategoryID;
import pt.amane.domain.exception.NotFoundException;
import pt.amane.domain.genre.Genre;
import pt.amane.domain.genre.GenreGateway;
import pt.amane.domain.genre.GenreID;

@IntegrationTest
public class GetGenreByIdUseCaseIT {

    @Autowired
    private GetGenreByIdUseCase useCase;

    @Autowired
    private CategoryGateway categoryGateway;

    @Autowired
    private GenreGateway genreGateway;

    @Test
    public void givenAValidId_whenCallsGetGenre_shouldReturnGenre() {
        // given
        final var series =
                categoryGateway.create(Category.newCategory("Séries", null, true));

        final var filmes =
                categoryGateway.create(Category.newCategory("Filmes", null, true));

        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(series.getId(), filmes.getId());

        final var aGenre = genreGateway.create(
                Genre.newGenre(expectedName, expectedIsActive)
                        .addCategories(expectedCategories)
        );

        final var expectedId = aGenre.getId();

        // when
        final var actualGenre = useCase.execute(expectedId.getValue());

        // then
        Assertions.assertEquals(expectedId.getValue(), actualGenre.id());
        Assertions.assertEquals(expectedName, actualGenre.name());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertTrue(
                expectedCategories.size() == actualGenre.categories().size()
                && asString(expectedCategories).containsAll(actualGenre.categories())
        );
        Assertions.assertEquals(aGenre.getCreatedAt(), actualGenre.createdAt());
        Assertions.assertEquals(aGenre.getUpdatedAt(), actualGenre.updatedAt());
        Assertions.assertEquals(aGenre.getDeletedAt(), actualGenre.deletedAt());
    }

    @Test
    public void givenAValidId_whenCallsGetGenreAndDoesNotExists_shouldReturnNotFound() {
        // given
        final var expectedErrorMessage = "Genre with ID 123 was not found";

        final var expectedId = GenreID.from("123");

        // when
        final var actualException = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.execute(expectedId.getValue());
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    private List<String> asString(final List<CategoryID> ids) {
        return ids.stream()
                .map(CategoryID::getValue)
                .toList();
    }
}
