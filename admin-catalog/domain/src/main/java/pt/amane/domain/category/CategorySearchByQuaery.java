package pt.amane.domain.category;

public record CategorySearchByQuaery(
    int page,
    int perPage,
    String terms,
    String sort,
    String direction
) {

}
