package pt.amane.application.video.delete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pt.amane.application.UseCaseTest;
import pt.amane.domain.exception.InternalErrorException;
import pt.amane.domain.video.MediaResourceGateway;
import pt.amane.domain.video.VideoGateway;
import pt.amane.domain.video.VideoID;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteVideoUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteVideoUseCase useCase;

    @Mock
    private VideoGateway videoGateway;

    @Mock
    private MediaResourceGateway mediaResourceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(videoGateway, mediaResourceGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteVideo_shouldDeleteIt() {
        // given
        final var expectedId = VideoID.unique();

        doNothing()
                .when(videoGateway).deleteById(any());

        doNothing()
                .when(mediaResourceGateway).clearResources(any());

        // when
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));

        // then
        verify(videoGateway).deleteById(eq(expectedId));
        verify(mediaResourceGateway).clearResources(eq(expectedId));
    }

    @Test
    public void givenAnInvalidId_whenCallsDeleteVideo_shouldBeOk() {
        // given
        final var expectedId = VideoID.from("1231");

        doNothing()
                .when(videoGateway).deleteById(any());

        // when
        Assertions.assertDoesNotThrow(() -> this.useCase.execute(expectedId.getValue()));

        // then
        verify(videoGateway).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenCallsDeleteVideoAndGatewayThrowsException_shouldReceiveException() {
        // given
        final var expectedId = VideoID.from("1231");

        doThrow(InternalErrorException.with("Error on delete video", new RuntimeException()))
                .when(videoGateway).deleteById(any());

        // when
        Assertions.assertThrows(
                InternalErrorException.class,
                () -> this.useCase.execute(expectedId.getValue())
        );

        // then
        verify(videoGateway).deleteById(eq(expectedId));
    }
}
