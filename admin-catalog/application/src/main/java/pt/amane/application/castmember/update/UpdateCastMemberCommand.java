package pt.amane.application.castmember.update;

import pt.amane.domain.castmember.CastMemberType;

public record UpdateCastMemberCommand(
        String id,
        String name,
        CastMemberType type
) {

    public static UpdateCastMemberCommand with(
            final String anId,
            final String aName,
            final CastMemberType aType
    ) {
        return new UpdateCastMemberCommand(anId, aName, aType);
    }
}
