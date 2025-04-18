package pt.amane.domain.video;

import pt.amane.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * Class VideoID generate Id from classes.
 */
public class VideoID extends Identifier {

    private final String value;

    private VideoID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Generate the unique ID from Video entity class for each Video
     * @return
     */
    public static VideoID unique() {
        return VideoID.from(UUID.randomUUID());
    }

    /**
     * Factory method that clone the VideoID contructor, either help to convert database value or test.
     * @param anId
     * @return
     */
    public static VideoID from(final String anId) {
        return new VideoID(anId);
    }

    public static VideoID from(final UUID anId) {
        return new VideoID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoID that = (VideoID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

