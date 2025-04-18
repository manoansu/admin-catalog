package pt.amane.infrastructure.configuration.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pt.amane.application.castmember.create.CreateCastMemberUseCase;
import pt.amane.application.castmember.create.DefaultCreateCastMemberUseCase;
import pt.amane.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import pt.amane.application.castmember.delete.DeleteCastMemberUseCase;
import pt.amane.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import pt.amane.application.castmember.retrieve.get.GetCastMemberByIdUseCase;
import pt.amane.application.castmember.retrieve.list.DefaultListCastMembersUseCase;
import pt.amane.application.castmember.retrieve.list.ListCastMembersUseCase;
import pt.amane.application.castmember.update.DefaultUpdateCastMemberUseCase;
import pt.amane.application.castmember.update.UpdateCastMemberUseCase;
import pt.amane.domain.castmember.CastMemberGateway;
import pt.amane.domain.category.CategoryGateway;

import java.util.Objects;

@Configuration
public class CastMemberUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final CastMemberGateway CastMemberGateway;

    public CastMemberUseCaseConfig(
            final CategoryGateway categoryGateway,
            final CastMemberGateway CastMemberGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.CastMemberGateway = Objects.requireNonNull(CastMemberGateway);
    }

    @Bean
    public CreateCastMemberUseCase createCastMemberUseCase() {
        return new DefaultCreateCastMemberUseCase(CastMemberGateway);
    }

    @Bean
    public DeleteCastMemberUseCase deleteCastMemberUseCase() {
        return new DefaultDeleteCastMemberUseCase(CastMemberGateway);
    }

    @Bean
    public GetCastMemberByIdUseCase getCastMemberByIdUseCase() {
        return new DefaultGetCastMemberByIdUseCase(CastMemberGateway);
    }

    @Bean
    public ListCastMembersUseCase listCastMemberUseCase() {
        return new DefaultListCastMembersUseCase(CastMemberGateway);
    }

    @Bean
    public UpdateCastMemberUseCase updateCastMemberUseCase() {
        return new DefaultUpdateCastMemberUseCase(CastMemberGateway);
    }
}
