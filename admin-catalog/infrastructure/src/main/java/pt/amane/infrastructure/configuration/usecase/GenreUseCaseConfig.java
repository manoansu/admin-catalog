package pt.amane.infrastructure.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.amane.application.genre.create.CreateGenreUseCase;
import pt.amane.application.genre.create.DefaultCreateGenreUseCase;
import pt.amane.application.genre.delete.DefaultDeleteGenreUseCase;
import pt.amane.application.genre.delete.DeleteGenreUseCase;
import pt.amane.application.genre.retrieve.get.DefaultGetGenreByIdUseCase;
import pt.amane.application.genre.retrieve.get.GetGenreByIdUseCase;
import pt.amane.application.genre.retrieve.list.DefaultListGenreUseCase;
import pt.amane.application.genre.retrieve.list.ListGenreUseCase;
import pt.amane.application.genre.update.DefaultUpdateGenreUseCase;
import pt.amane.application.genre.update.UpdateGenreUseCase;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.genre.GenreGateway;

import java.util.Objects;

@Configuration
public class GenreUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final GenreGateway genreGateway;

    public GenreUseCaseConfig(
            final CategoryGateway categoryGateway,
            final GenreGateway genreGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Bean
    public CreateGenreUseCase createGenreUseCase() {
        return new DefaultCreateGenreUseCase(categoryGateway, genreGateway);
    }

    @Bean
    public DeleteGenreUseCase deleteGenreUseCase() {
        return new DefaultDeleteGenreUseCase(genreGateway);
    }

    @Bean
    public GetGenreByIdUseCase getGenreByIdUseCase() {
        return new DefaultGetGenreByIdUseCase(genreGateway);
    }

    @Bean
    public ListGenreUseCase listGenreUseCase() {
        return new DefaultListGenreUseCase(genreGateway);
    }

    @Bean
    public UpdateGenreUseCase updateGenreUseCase() {
        return new DefaultUpdateGenreUseCase(categoryGateway, genreGateway);
    }
}
