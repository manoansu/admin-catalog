package pt.amane.infrastructure.castmember.models;


import pt.amane.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}
