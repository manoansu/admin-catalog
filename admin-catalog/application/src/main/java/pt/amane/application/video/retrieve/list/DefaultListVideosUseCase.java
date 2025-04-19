package pt.amane.application.video.retrieve.list;

import pt.amane.domain.pagination.Pagination;
import pt.amane.domain.video.VideoGateway;
import pt.amane.domain.video.VideoSearchQuery;

import java.util.Objects;

public class DefaultListVideosUseCase extends ListVideosUseCase {

    private final VideoGateway videoGateway;

    public DefaultListVideosUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public Pagination<VideoListOutput> execute(final VideoSearchQuery aQuery) {
        return this.videoGateway.findAll(aQuery)
                .map(VideoListOutput::from);
    }
}
