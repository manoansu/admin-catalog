package pt.amane.application.castmember.delete;

import pt.amane.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
    extends UnitUseCase<String>
    permits DefaultDeleteCastMemberUseCase {
}
