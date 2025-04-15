package pt.amane.application.castmember.retrieve.get;

import pt.amane.domain.castmember.CastMember;
import pt.amane.domain.castmember.CastMemberGateway;
import pt.amane.domain.castmember.CastMemberID;
import pt.amane.domain.exception.NotFoundException;

import java.util.Objects;

public non-sealed class DefaultGetCastMemberByIdUseCase extends GetCastMemberByIdUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultGetCastMemberByIdUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public CastMemberOutput execute(final String anId) {
        final var aMemberId = CastMemberID.from(anId);
        return castMemberGateway.findById(aMemberId)
            .map(CastMemberOutput::from)
            .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberId));
    }
}
