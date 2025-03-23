package pt.amane.infrastructure.utils;

import org.springframework.data.jpa.domain.Specification;

/**
 * Classe final para ninguem extende essa classe.
 */
public final class SpecificationUtils {

  //Constructor privado para ninguem instancia essa classe.
  private SpecificationUtils(){}


  public static <T> Specification<T> like(final String prop, final String term) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(criteriaBuilder.upper(root.get(prop)), like(term.toUpperCase()));
  }

  private static String like(String term) {
    return "%" + term + "%";
  }

}
