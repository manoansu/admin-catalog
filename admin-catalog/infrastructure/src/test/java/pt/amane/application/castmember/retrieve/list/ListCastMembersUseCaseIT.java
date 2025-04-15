package pt.amane.application.castmember.retrieve.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pt.amane.IntegrationTest;
import pt.amane.domain.castmember.CastMember;
import pt.amane.domain.castmember.CastMemberGateway;
import pt.amane.domain.castmember.CastMemberType;
import pt.amane.domain.pagination.SearchQuery;
import pt.amane.infrastructure.castmember.persistence.CastMemberJpaEntity;
import pt.amane.infrastructure.castmember.persistence.CastMemberRepository;

import java.util.List;

@IntegrationTest
public class ListCastMembersUseCaseIT {

    @Autowired
    private DefaultListCastMembersUseCase useCase;

    @Autowired
    private CastMemberRepository castMemberRepository;

    @MockitoBean
    private CastMemberGateway castMemberGateway;


    @Test
    void givenAValidQuery_WhenCallsListCastMembers_shouldReturnAll() {

        //given
        final  var members = List.of(
                CastMember.newMember("William Shatner", CastMemberType.DIRECTOR),
                CastMember.newMember("Leonard Nimoy", CastMemberType.ACTOR));

        this.castMemberRepository.saveAllAndFlush(
                members.stream().map(CastMemberJpaEntity::from).toList()
        );

        Assertions.assertEquals(2, castMemberRepository.count());

        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedTotal = 2;

        final var expectedItems = members.stream().map(CastMemberListOutput::from).toList();

        final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        //when
        final var actualOutput = useCase.execute(aQuery);

        //then
        Assertions.assertEquals(expectedPage, actualOutput.currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.total());
        Assertions.assertTrue(
                expectedItems.size() == actualOutput.items().size()
        && expectedItems.containsAll(actualOutput.items()));

        Mockito.verify(castMemberGateway).findAll(Mockito.any());

    }

    @Test
    void givenAValidQuery_WhenCallsListCastMembersAndIsEmpty_shouldReturn() {

        //given
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";
        final var expectedTotal = 2;


        final var expectedItems = List.of();

        Assertions.assertEquals(0, castMemberRepository.count());

        final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        //when
        final var actualOutput = useCase.execute(aQuery);

        //then
        Assertions.assertEquals(expectedPage, actualOutput.currentPage());
        Assertions.assertEquals(expectedPerPage, actualOutput.perPage());
        Assertions.assertEquals(expectedTotal, actualOutput.total());
        Assertions.assertEquals(expectedItems, actualOutput.items());

        Mockito.verify(castMemberGateway).findAll(Mockito.any());

    }

    @Test
    void givenAValidQuery_WhenCallsListCastMembersAndGatewayThrowsRandomException_shouldException() {

        //given
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "";
        final var expectedSort = "createdAt";
        final var expectedDirection = "asc";

        final var expectedErrorMessage = "Gateway error";

        Mockito.doThrow(new IllegalStateException(expectedErrorMessage))
                .when(castMemberGateway).findAll(Mockito.any());

        Assertions.assertEquals(0, castMemberRepository.count());

        final var aQuery = new SearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        //when
        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> {
            useCase.execute(aQuery);
        });

        //then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(castMemberGateway).findAll(Mockito.any());

    }
}
