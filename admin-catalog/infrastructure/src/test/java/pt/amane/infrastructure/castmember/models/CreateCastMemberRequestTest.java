package pt.amane.infrastructure.castmember.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import pt.amane.JacksonTest;
import pt.amane.domain.castmember.CastMemberType;


@JacksonTest
class CreateCastMemberRequestTest {

    @Autowired
    private JacksonTester<CreateCastMemberRequest> json;

    @Test
    public void testUmMarshall() throws Exception {
        final var expectedName = "vin diesel";
        final var expectedType = CastMemberType.DIRECTOR.toString();

        final var json = """
                {
                    "name": "%s"
                    "type": "%s"
                }
                """.formatted(expectedName,expectedType);

        final var actualJson = this.json.parse(json);

        Assertions.assertThat(actualJson)
                .hasFieldOrPropertyWithValue("$.name", expectedName)
                .hasFieldOrPropertyWithValue("$.type", expectedType);
    }

}