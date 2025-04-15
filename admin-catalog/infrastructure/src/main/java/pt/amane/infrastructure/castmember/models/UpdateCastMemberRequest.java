package pt.amane.infrastructure.castmember.models;

import pt.amane.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
