package pt.amane.application.category.retrieve.get;

import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import pt.amane.IntegrationTest;
import pt.amane.domain.category.Category;
import pt.amane.domain.category.CategoryGateway;
import pt.amane.domain.category.CategoryID;
import pt.amane.domain.exception.DomainException;
import pt.amane.infrastructure.category.persistence.CategoryJpaEntity;
import pt.amane.infrastructure.category.persistence.CategoryRepository;


@IntegrationTest
public class GetCategoryByIdUseCaseITT {

  @Autowired
  private GetCategoryByIdUseCase useCase;

  @Autowired
  private CategoryRepository categoryRepository;

  @MockitoBean // Permite mockar as ações do mokito no teste de integração.
  private CategoryGateway categoryGateway;

  @Test
  void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
    final var expectedName = "Filmes";
    final var expectedDescription = "A categoria mais assistida";
    final var expectedIsActive = true;

    final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

    final var expectedId = aCategory.getId();

    Mockito.doReturn(Optional.of(Category.with(aCategory)))
            .when(categoryGateway).findById(eq(expectedId));
    
    //TODO Ao tentar salvar o metodo abaixo a linha de baixo nao encontra o valor de Id salvo, para verificar
//    save(aCategory);

    final var actualCategory = useCase.execute(expectedId.getValue());

    Assertions.assertEquals(expectedId, actualCategory.id());
    Assertions.assertEquals(expectedName, actualCategory.name());
    Assertions.assertEquals(expectedDescription, actualCategory.description());
    Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
    Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
    Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
    Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());

  }

  @Test
  void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
    final var expectedId = CategoryID.from("123");
    final var expectedErrorMessage = "Category with ID 123 was not found";

    final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(expectedId.getValue()));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

  }

  @Test
  void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
    final var expectedId = CategoryID.from("123");
    final var expectedErrorMessage = "'Gateway error";

    Mockito.doThrow(new IllegalStateException(expectedErrorMessage))
            .when(categoryGateway).findById(Mockito.eq(expectedId));

    final var actualException = Assertions.assertThrows(IllegalStateException.class,
        () -> useCase.execute(expectedId.getValue()));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

  }

  private void save(final Category... aCategory) {
    categoryRepository.saveAllAndFlush(
        Arrays.stream(aCategory)
            .map(CategoryJpaEntity::from)
            .toList()
    );
  }

}
