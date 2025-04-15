package pt.amane.application.castmember.retrieve.get;

import pt.amane.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase
    extends UseCase<String, CastMemberOutput>
    permits DefaultGetCastMemberByIdUseCase {
}
