package pt.amane.application.video.media.update;

import pt.amane.application.video.media.get.GetMediaCommand;
import pt.amane.application.video.media.get.GetMediaUseCase;
import pt.amane.application.video.media.get.MediaOutput;
import pt.amane.domain.exception.NotFoundException;
import pt.amane.domain.validaion.Error;
import pt.amane.domain.video.MediaResourceGateway;
import pt.amane.domain.video.VideoID;
import pt.amane.domain.video.VideoMediaType;

import java.util.Objects;

public class DefaultUpdateMediaStatusUseCase  extends UpdateMediaStatusUseCase {

    private final MediaResourceGateway mediaResourceGateway;

    public DefaultUpdateMediaStatusUseCase(final MediaResourceGateway mediaResourceGateway) {
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
    }

    @Override
    public void execute(final UpdateMediaStatusCommand aCmd) {
        final var anId = VideoID.from(aCmd.videoId());
        final var aType = VideoMediaType.of(aCmd.mediaType())
                .orElseThrow(() -> typeNotFound(aCmd.mediaType()));

        final var aResource =
                this.mediaResourceGateway.getResource(anId, aType)
                        .orElseThrow(() -> notFound(aCmd.videoId(), aCmd.mediaType()));

        return MediaOutput.with(aResource);
    }

    private NotFoundException notFound(final String anId, final String aType) {
        return NotFoundException.with(new Error("Resource %s not found for video %s".formatted(aType, anId)));
    }

    private NotFoundException typeNotFound(final String aType) {
        return NotFoundException.with(new Error("Media type %s doesn't exists".formatted(aType)));
    }

}

