package pt.amane.application.video.delete;

import pt.amane.domain.video.MediaResourceGateway;
import pt.amane.domain.video.VideoGateway;
import pt.amane.domain.video.VideoID;

import java.util.Objects;

public class DefaultDeleteVideoUseCase extends DeleteVideoUseCase {

    private final VideoGateway videoGateway;
    private final MediaResourceGateway mediaResourceGateway;
    public DefaultDeleteVideoUseCase(
            final VideoGateway videoGateway,
            final MediaResourceGateway mediaResourceGateway
    ) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
    }

    @Override
    public void execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        this.videoGateway.deleteById(aVideoId);
        this.mediaResourceGateway.clearResources(aVideoId);
    }
}
