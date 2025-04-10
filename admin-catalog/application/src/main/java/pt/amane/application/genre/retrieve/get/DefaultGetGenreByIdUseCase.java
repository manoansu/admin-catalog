package pt.amane.application.genre.retrieve.get;

import java.util.Objects;
import pt.amane.domain.exception.NotFoundException;
import pt.amane.domain.genre.Genre;
import pt.amane.domain.genre.GenreGateway;
import pt.amane.domain.genre.GenreID;

public class DefaultGetGenreByIdUseCase extends GetGenreByIdUseCase{

    private final GenreGateway genreGateway;

    public DefaultGetGenreByIdUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public GenreOutput execute(final String anIn) {
        final var aGenreId = GenreID.from(anIn);
        return this.genreGateway.findById(aGenreId)
                .map(GenreOutput::from)
                .orElseThrow(() -> NotFoundException.with(Genre.class, aGenreId));
    }
}
