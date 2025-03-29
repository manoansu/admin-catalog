package pt.amane.domain.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class InstantUtils {

    private InstantUtils() {
    }

    // evita que o Instant gera até  9 casa = NANOSECONDS, mas sim, 6 casa = MICROSECONDS
    // Em alguns computador devolve 6 casa = MICROSECONDS
    public static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
