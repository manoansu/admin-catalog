package pt.amane.infrastructure.castmember.presenter;


import pt.amane.application.castmember.retrieve.get.CastMemberOutput;
import pt.amane.application.castmember.retrieve.list.CastMemberListOutput;
import pt.amane.infrastructure.castmember.models.CastMemberListResponse;
import pt.amane.infrastructure.castmember.models.CastMemberResponse;

public interface CastMemberPresenter {

    static CastMemberResponse present(final CastMemberOutput aMember) {
        return new CastMemberResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString(),
                aMember.updatedAt().toString()
        );
    }

    static CastMemberListResponse present(final CastMemberListOutput aMember) {
        return new CastMemberListResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString()
        );
    }
}
