package pt.amane.application.genre.delete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pt.amane.IntegrationTest;
import pt.amane.domain.genre.Genre;
import pt.amane.domain.genre.GenreGateway;
import pt.amane.domain.genre.GenreID;
import pt.amane.infrastructure.genre.persistence.GenreRepository;

@IntegrationTest
public class DeleteGenreUseCaseIT {

    @Autowired
    private DeleteGenreUseCase useCase;

    @Autowired
    private GenreGateway genreGateway;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void givenAValidGenreId_whenCallsDeleteGenre_shouldDeleteGenre() {
        // given
        final var aGenre = genreGateway.create(Genre.newGenre("Ação", true));

        final var expectedId = aGenre.getId();

        Assertions.assertEquals(1, genreRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // when
        Assertions.assertEquals(0, genreRepository.count());
    }

    @Test
    public void givenAnInvalidGenreId_whenCallsDeleteGenre_shouldBeOk() {
        // given
        genreGateway.create(Genre.newGenre("Ação", true));

        final var expectedId = GenreID.from("123");

        Assertions.assertEquals(1, genreRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // when
        Assertions.assertEquals(1, genreRepository.count());
    }
}
