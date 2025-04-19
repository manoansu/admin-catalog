package pt.amane.application.video.media.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pt.amane.application.UseCaseTest;
import pt.amane.domain.exception.NotFoundException;
import pt.amane.domain.utils.FixtureUtils;
import pt.amane.domain.video.MediaResourceGateway;
import pt.amane.domain.video.VideoID;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class GetMediaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetMediaUseCase useCase;

    @Mock
    private MediaResourceGateway mediaResourceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(mediaResourceGateway);
    }

    @Test
    public void givenVideoIdAndType_whenIsValidCmd_shouldReturnResource() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedType = FixtureUtils.Videos.mediaType();
        final var expectedResource = FixtureUtils.Videos.resource(expectedType);

        when(mediaResourceGateway.getResource(expectedId, expectedType))
                .thenReturn(Optional.of(expectedResource));

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), expectedType.name());

        // when
        final var actualResult = this.useCase.execute(aCmd);

        // then
        Assertions.assertEquals(expectedResource.name(), actualResult.name());
        Assertions.assertEquals(expectedResource.content(), actualResult.content());
        Assertions.assertEquals(expectedResource.contentType(), actualResult.contentType());
    }

    @Test
    public void givenVideoIdAndType_whenIsNotFound_shouldReturnNotFoundException() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedType = FixtureUtils.Videos.mediaType();

        when(mediaResourceGateway.getResource(expectedId, expectedType))
                .thenReturn(Optional.empty());

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), expectedType.name());

        // when
        Assertions.assertThrows(NotFoundException.class, () -> {
            this.useCase.execute(aCmd);
        });
    }

    @Test
    public void givenVideoIdAndType_whenTypeDoesntExists_shouldReturnNotFoundException() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedErrorMessage = "Media type QUALQUER doesn't exists";

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), "QUALQUER");

        // when
        final var actualException = Assertions.assertThrows(NotFoundException.class, () -> {
            this.useCase.execute(aCmd);
        });

        // then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
