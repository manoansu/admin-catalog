package pt.amane.application.castmember.retrieve.list;

import pt.amane.domain.castmember.CastMemberGateway;
import pt.amane.domain.pagination.Pagination;
import pt.amane.domain.pagination.SearchQuery;

import java.util.Objects;

public non-sealed class DefaultListCastMembersUseCase extends ListCastMembersUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultListCastMembersUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public Pagination<CastMemberListOutput> execute(final SearchQuery aQuary) {
        return castMemberGateway.findAll(aQuary)
            .map(CastMemberListOutput::from);
    }

}
